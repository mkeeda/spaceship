package dev.mkeeda.spaceship.infra.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mkeeda.spaceship.domain.repository.KintoneNotificationRepository
import dev.mkeeda.spaceship.infra.repositoryimpl.KintoneNotificationRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindKintoneNotificationRepository(impl: KintoneNotificationRepositoryImpl): KintoneNotificationRepository
}
