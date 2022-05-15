package dev.mkeeda.spaceship.infra.api

import android.util.Base64
import dev.mkeeda.spaceship.data.credential.SecureAccessConfig
import dev.mkeeda.spaceship.data.credential.ifSecureAccess
import dev.mkeeda.spaceship.infra.BuildConfig
import dev.mkeeda.spaceship.infra.datasource.LoginCredentialDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.network.tls.addKeyStore
import io.ktor.serialization.kotlinx.json.json
import java.security.KeyStore
import javax.inject.Inject
import kotlinx.serialization.json.Json

internal interface KintoneApiServiceBuilder {
    suspend fun build(): KintoneApiService
}

internal class KintoneApiServiceBuilderImpl @Inject constructor(
    private val loginCredentialDataSource: LoginCredentialDataSource
) : KintoneApiServiceBuilder {
    override suspend fun build(): KintoneApiService {
        // TODO memory cache
        val loginCredential = loginCredentialDataSource.readLoginCredential()

        val httpClient = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
            }
            loginCredential.environmentConfig.ifSecureAccess { secureAccessConfig ->
                engine {
                    https {
                        addKeyStore(
                            store = secureAccessConfig.toKeyStore(),
                            password = secureAccessConfig.clientCertPassword.toCharArray()
                        )
                    }
                }
            }
        }

        return KintoneApiService(
            httpClient = httpClient,
            loginCredential = loginCredential
        )
    }
}

private fun SecureAccessConfig.toKeyStore(): KeyStore {
    val keyStoreFile = Base64.decode(clientCertBase64, Base64.DEFAULT).inputStream()
    val keyStorePassword = clientCertPassword.toCharArray()
    val keyStore = KeyStore.getInstance("PKCS12").also {
        it.load(keyStoreFile, keyStorePassword)
    }
    return keyStore
}
