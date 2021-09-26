package dev.mkeeda.spaceship.domain.usecase

import kotlinx.coroutines.flow.Flow

interface UseCase<P, O> {
    fun execute(param: P): Flow<DomainLoadState<O>>
}

interface NoParamUseCase<O> {
    fun execute(): Flow<DomainLoadState<O>>
}
