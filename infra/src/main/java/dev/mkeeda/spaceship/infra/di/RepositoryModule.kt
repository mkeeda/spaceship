package dev.mkeeda.spaceship.infra.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mkeeda.spaceship.domain.repository.LoginCredentialRepository
import dev.mkeeda.spaceship.domain.repository.PostDetailsRepository
import dev.mkeeda.spaceship.domain.repository.ThreadRepository
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import dev.mkeeda.spaceship.infra.repositoryimpl.LoginCredentialRepositoryImpl
import dev.mkeeda.spaceship.infra.repositoryimpl.PostDetailsRepositoryImpl
import dev.mkeeda.spaceship.infra.repositoryimpl.ThreadRepositoryImpl
import dev.mkeeda.spaceship.infra.repositoryimpl.TimelineRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTimelineRepository(impl: TimelineRepositoryImpl): TimelineRepository

    @Binds
    abstract fun bindPostDetailsRepository(impl: PostDetailsRepositoryImpl): PostDetailsRepository

    @Binds
    abstract fun bindThreadRepository(impl: ThreadRepositoryImpl): ThreadRepository

    @Binds
    abstract fun bindLoginCredentialRepository(impl: LoginCredentialRepositoryImpl):LoginCredentialRepository
}
