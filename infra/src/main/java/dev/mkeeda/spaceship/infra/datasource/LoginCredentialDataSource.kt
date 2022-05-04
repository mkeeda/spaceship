package dev.mkeeda.spaceship.infra.datasource

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplates
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.mkeeda.spaceship.data.credential.LoginCredential
import dev.mkeeda.spaceship.data.credential.NoLoginCredentialException
import java.io.InputStream
import java.io.OutputStream
import java.security.GeneralSecurityException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.first
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Singleton
internal class LoginCredentialDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore by dataStore(
        fileName = "login_credential",
        serializer = LoginCredentialSerializer(context)
    )

    suspend fun writeLoginCredential(newCredential: LoginCredential) {
        context.dataStore.updateData { newCredential }
    }

    suspend fun readLoginCredential(): LoginCredential {
        val loginCredential = context.dataStore.data.first()
        return if (loginCredential == LoginCredential.None) {
            throw NoLoginCredentialException()
        } else {
            loginCredential
        }
    }
}

private class LoginCredentialSerializer(private val context: Context) : Serializer<LoginCredential> {
    private val aead: Aead by lazy {
        AeadConfig.register()
        AndroidKeysetManager.Builder()
            .withSharedPref(
                context,
                "master_keyset",
                "master_key_preference"
            )
            .withKeyTemplate(KeyTemplates.get("AES256_GCM"))
            .withMasterKeyUri("android-keystore://master_key")
            .build()
            .keysetHandle
            .getPrimitive(Aead::class.java)
    }

    override val defaultValue: LoginCredential = LoginCredential.None

    override suspend fun readFrom(input: InputStream): LoginCredential {
        val encrypted = input.readBytes()

        try {
            val decrypted = aead.decrypt(encrypted, null)

            return Json.decodeFromString(String(decrypted))
        } catch (exception: GeneralSecurityException) {
            throw CorruptionException("Cannot decrypt.", exception)
        }
    }

    override suspend fun writeTo(t: LoginCredential, output: OutputStream) {
        val json = Json.encodeToString(t)
        val encrypted = aead.encrypt(json.toByteArray(), null)
        // This method run I/O thread
        output.write(encrypted)
    }
}
