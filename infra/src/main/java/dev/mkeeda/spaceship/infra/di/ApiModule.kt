package dev.mkeeda.spaceship.infra.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mkeeda.spaceship.infra.BuildConfig
import dev.mkeeda.spaceship.infra.api.ntf.NtfGetService
import dev.mkeeda.spaceship.infra.api.ntf.NtfGetServiceImpl
import dev.mkeeda.spaceship.infra.api.ntf.NtfListService
import dev.mkeeda.spaceship.infra.api.ntf.NtfListServiceImpl
import dev.mkeeda.spaceship.infra.api.thread.ThreadPostListService
import dev.mkeeda.spaceship.infra.api.thread.ThreadPostListServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
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
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ServiceModule {
    @Binds
    abstract fun bindNtfListService(impl: NtfListServiceImpl): NtfListService

    @Binds
    abstract fun bindNtfGetService(impl: NtfGetServiceImpl): NtfGetService

    @Binds
    abstract fun bindThreadPostListService(impl: ThreadPostListServiceImpl): ThreadPostListService
}
