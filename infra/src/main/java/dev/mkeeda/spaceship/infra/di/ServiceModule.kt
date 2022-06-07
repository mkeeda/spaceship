package dev.mkeeda.spaceship.infra.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mkeeda.spaceship.infra.api.KintoneApiServiceBuilder
import dev.mkeeda.spaceship.infra.api.KintoneApiServiceBuilderImpl
import dev.mkeeda.spaceship.infra.api.ntf.NtfGetService
import dev.mkeeda.spaceship.infra.api.ntf.NtfGetServiceImpl
import dev.mkeeda.spaceship.infra.api.ntf.NtfListService
import dev.mkeeda.spaceship.infra.api.ntf.NtfListServiceImpl
import dev.mkeeda.spaceship.infra.api.thread.ThreadPostListService
import dev.mkeeda.spaceship.infra.api.thread.ThreadPostListServiceImpl

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

@Module
@InstallIn(SingletonComponent::class)
internal abstract class KintoneServiceBuilderModule {
    @Binds
    abstract fun bindBuilder(impl: KintoneApiServiceBuilderImpl): KintoneApiServiceBuilder
}
