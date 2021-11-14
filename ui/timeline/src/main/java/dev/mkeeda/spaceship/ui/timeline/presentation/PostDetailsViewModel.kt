package dev.mkeeda.spaceship.ui.timeline.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dev.mkeeda.spaceship.data.Conversation
import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.domain.usecase.ObservePostDetails
import dev.mkeeda.spaceship.domain.usecase.Success
import dev.mkeeda.spaceship.ui.common.dataflow.Presentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Dagger assisted injection is not working using value classes.
 * https://github.com/google/dagger/issues/2855
 * So use Long as primitive type instead of PostId.
 */
class PostDetailsViewModel @AssistedInject constructor(
    @Assisted private val postId: Long,
    observePostDetails: ObservePostDetails
) : ViewModel(), Presentation<PostDetailsViewState, Nothing, Nothing> {

    override val state: StateFlow<PostDetailsViewState> = observePostDetails.output
        .onEach { println(it) }
        .filterIsInstance<Success<Conversation>>()
        .map { success ->
            PostDetailsViewState(
                conversation = success.data
            )
        }.stateIn(
            initialValue = PostDetailsViewState.Initial,
            scope = viewModelScope,
            started = SharingStarted.Lazily
        )

    override fun event(event: Nothing) {
        TODO("Not yet implemented")
    }

    override val effect: Flow<Nothing>
        get() = TODO("Not yet implemented")

    init {
        viewModelScope.launch {
            observePostDetails.execute(param = PostId(postId))
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(postId: Long): PostDetailsViewModel
    }
}
