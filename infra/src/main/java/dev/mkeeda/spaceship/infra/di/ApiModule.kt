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
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    json = Json {
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
}
