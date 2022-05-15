package dev.mkeeda.spaceship.infra.api

import dev.mkeeda.spaceship.infra.BuildConfig
import dev.mkeeda.spaceship.infra.datasource.LoginCredentialDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Inject
import kotlinx.serialization.json.Json

internal interface KintoneApiServiceBuilder {
    suspend fun build(): KintoneApiService
}

private val defaultHttpClient = HttpClient(CIO) {
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
}

internal class KintoneApiServiceBuilderImpl @Inject constructor(
    private val loginCredentialDataSource: LoginCredentialDataSource
) : KintoneApiServiceBuilder {
    override suspend fun build(): KintoneApiService {
        val loginCredential = loginCredentialDataSource.readLoginCredential()
        return KintoneApiService(
            httpClient = defaultHttpClient,
            loginCredential = loginCredential
        )
    }
}
