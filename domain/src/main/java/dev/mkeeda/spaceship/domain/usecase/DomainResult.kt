package dev.mkeeda.spaceship.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface DomainResult<out T>
sealed interface DomainLoadState<out T>

data class Success<T>(val data: T) : DomainResult<T>, DomainLoadState<T>
data class Error(val cause: Throwable) : DomainResult<Nothing>, DomainLoadState<Nothing>
object Loading : DomainLoadState<Nothing>

fun <T> Flow<T>.toLoadState(): Flow<DomainLoadState<T>> {
    return map<T, DomainLoadState<T>> { data ->
        Success(data)
    }.onStart {
        emit(Loading)
    }.catch { cause ->
        emit(Error(cause))
    }
}
