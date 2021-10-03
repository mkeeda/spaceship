package dev.mkeeda.spaceship.ui.timeline.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dev.mkeeda.spaceship.ui.common.dataflow.Presentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

/**
 * Dagger assisted injection is not working using value classes.
 * https://github.com/google/dagger/issues/2855
 * So use Long as primitive type instead of PostId.
 */
class PostDetailsViewModel @AssistedInject constructor(
   @Assisted private val postId: Long
) : ViewModel(), Presentation<PostDetailsViewState, Nothing, Nothing> {
    init {
        println(postId)
    }

    override val state: StateFlow<PostDetailsViewState> = flow<PostDetailsViewState> {
    }.stateIn(
        initialValue = PostDetailsViewState.fake,
        scope = viewModelScope,
        started = SharingStarted.Lazily
    )

    override fun event(event: Nothing) {
        TODO("Not yet implemented")
    }

    override val effect: Flow<Nothing>
        get() = TODO("Not yet implemented")

    @AssistedFactory
    interface Factory {
        fun create(postId: Long): PostDetailsViewModel
    }
}
