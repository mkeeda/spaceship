package dev.mkeeda.spaceship.infra.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.mkeeda.spaceship.data.credential.LoginCredential
import dev.mkeeda.spaceship.data.credential.NoLoginCredentialException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Singleton
internal class LoginCredentialDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore by preferencesDataStore("login_credential")
    private val credentialJsonKey = stringPreferencesKey("credential_json")

    suspend fun writeLoginCredential(newCredential: LoginCredential) {
        val json = Json.encodeToString(newCredential)
        context.dataStore.edit { preferences ->
            preferences[credentialJsonKey] = json
        }
    }

    suspend fun readLoginCredential(): LoginCredential {
        val loginCredentialJson = context.dataStore.data
            .map { preferences ->
                preferences[credentialJsonKey]
            }.first()

        return loginCredentialJson?.let {
            Json.decodeFromString(it)
        } ?: throw NoLoginCredentialException()
    }
}
