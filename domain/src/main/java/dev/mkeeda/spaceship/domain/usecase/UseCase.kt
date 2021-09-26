package dev.mkeeda.spaceship.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest

abstract class UseCase<P, O> {
    private val paramEvent = MutableSharedFlow<P>()

    val output: Flow<DomainLoadState<O>> = paramEvent
        .flatMapLatest {
            useCaseFlow(param = it)
        }.toLoadState()

    protected abstract fun useCaseFlow(param: P): Flow<O>

    suspend fun execute(param: P) {
        paramEvent.emit(param)
    }
}

abstract class NoParamUseCase<O> : UseCase<Unit, O>() {
    final override fun useCaseFlow(param: Unit): Flow<O> = useCaseFlow()

    protected abstract fun useCaseFlow(): Flow<O>

    suspend fun execute() = execute(param = Unit)
}
