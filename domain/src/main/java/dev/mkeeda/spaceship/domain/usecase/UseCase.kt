package dev.mkeeda.spaceship.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest

abstract class UseCase<P, O> {
    private val paramEvent = MutableSharedFlow<P>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val output: Flow<DomainLoadState<O>> = paramEvent
        .flatMapLatest {
            useCaseFlow(param = it)
        }.toLoadState()

    protected abstract fun useCaseFlow(param: P): Flow<O>

    fun execute(param: P) {
        paramEvent.tryEmit(param)
    }
}

abstract class NoParamUseCase<O> : UseCase<Unit, O>() {
    final override fun useCaseFlow(param: Unit): Flow<O> = useCaseFlow()

    protected abstract fun useCaseFlow(): Flow<O>

    fun execute() = execute(param = Unit)
}

abstract class PagingUseCase<P, O: Any> {
    private val paramEvent = MutableSharedFlow<PagingParam<P>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val output: Flow<PagingData<O>> = paramEvent
        .flatMapLatest {
            useCaseFlow(config = it.config, param = it.param)
        }

    protected abstract fun useCaseFlow(config: PagingConfig, param: P): Flow<PagingData<O>>

    fun execute(config: PagingConfig, param: P) {
        paramEvent.tryEmit(PagingParam(config, param))
    }

    private data class PagingParam<P>(
        val config: PagingConfig,
        val param: P
    )
}

abstract class NoParamPagingUseCase<O: Any> : PagingUseCase<Unit, O>() {
    final override fun useCaseFlow(config: PagingConfig, param: Unit): Flow<PagingData<O>> = useCaseFlow(config)

    protected abstract fun useCaseFlow(config: PagingConfig): Flow<PagingData<O>>

    fun execute(config: PagingConfig) = execute(config = config, param = Unit)
}
