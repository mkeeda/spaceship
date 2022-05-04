package dev.mkeeda.spaceship.domain.usecase.base

import dev.mkeeda.spaceship.domain.usecase.DomainLoadState
import dev.mkeeda.spaceship.domain.usecase.toLoadState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest

abstract class UseCase<P, O> {
    private val paramEvent = MutableSharedFlow<P>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val output: Flow<O> = paramEvent
        .flatMapLatest {
            useCaseFlow(param = it)
        }

    protected abstract fun useCaseFlow(param: P): Flow<O>

    fun execute(param: P) {
        paramEvent.tryEmit(param)
    }
}

val <P, O> UseCase<P, O>.loadState: Flow<DomainLoadState<O>>
    get() = output.toLoadState()

abstract class NoParamUseCase<O> : UseCase<Unit, O>() {
    final override fun useCaseFlow(param: Unit): Flow<O> = useCaseFlow()

    protected abstract fun useCaseFlow(): Flow<O>

    fun execute() = execute(param = Unit)
}
