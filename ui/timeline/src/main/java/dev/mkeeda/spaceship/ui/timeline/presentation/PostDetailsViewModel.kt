package dev.mkeeda.spaceship.ui.timeline.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.PostingLocation
import dev.mkeeda.spaceship.domain.usecase.Loading
import dev.mkeeda.spaceship.domain.usecase.ShowSelectedPostDetail
import dev.mkeeda.spaceship.domain.usecase.ShowSpacePostContext
import dev.mkeeda.spaceship.domain.usecase.Success
import dev.mkeeda.spaceship.ui.common.dataflow.Presentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Dagger assisted injection is not working using value classes.
 * https://github.com/google/dagger/issues/2855
 * So use Long as primitive type instead of PostId.
 */
class PostDetailsViewModel @AssistedInject constructor(
    @Assisted private val postId: Long,
    showSelectedPostDetail: ShowSelectedPostDetail,
    private val showSpacePostContext: ShowSpacePostContext,
) : ViewModel(), Presentation<PostDetailsViewState, PostDetailsEvent, Nothing> {

    override val state: StateFlow<PostDetailsViewState> = combine(
        showSelectedPostDetail.output,
        showSpacePostContext.output
    ) { selectedPostDetail, spacePostContext ->
        when {
            selectedPostDetail is Success && spacePostContext is Loading -> {
                PostDetailsViewState(
                    comments = listOf(selectedPostDetail.data),
                    focusedCommentsIndex = 0
                )
            }
            selectedPostDetail is Success && spacePostContext is Success -> {
                val allComments = spacePostContext.data
                PostDetailsViewState(
                    comments = allComments,
                    focusedCommentsIndex = allComments
                        .indexOf(selectedPostDetail.data)
                        .takeIf { it >= 0 } ?: 0
                )
            }
            else -> {
                PostDetailsViewState.Initial
            }
        }
    }.stateIn(
        initialValue = PostDetailsViewState.Initial,
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
    )

    private val postDetailsEvent = MutableSharedFlow<PostDetailsEvent>()

    override fun event(event: PostDetailsEvent) {
        viewModelScope.launch {
            postDetailsEvent.emit(event)
        }
    }

    override val effect: Flow<Nothing>
        get() = TODO("Not yet implemented")

    init {
        viewModelScope.launch {
            postDetailsEvent.collect { event ->
                when (event) {
                    is PostDetailsEvent.ShowContext -> showContext(event)
                }
            }
        }

        showSelectedPostDetail.execute(param = PostId(postId))
    }

    private fun showContext(event: PostDetailsEvent.ShowContext) {
        when (event.location) {
            is PostingLocation.App -> TODO()
            PostingLocation.Message -> TODO()
            is PostingLocation.People -> TODO()
            is PostingLocation.Space -> showSpacePostContext.execute(param = event.location)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(postId: Long): PostDetailsViewModel
    }
}
