package dev.mkeeda.spaceship.ui.timeline.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.domain.usecase.ObserveTimeline
import dev.mkeeda.spaceship.ui.common.dataflow.Presentation
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class TimelineViewModel @Inject constructor(
    observeTimeline: ObserveTimeline
) : ViewModel(), Presentation<TimelineViewState, Nothing, Nothing> {

    override val state: StateFlow<TimelineViewState>
        get() = TODO("Not yet implemented")

    val pagingTimeline: Flow<PagingData<TimelinePost>> =
        observeTimeline.output.cachedIn(viewModelScope)

    override fun event(event: Nothing) {
        TODO("Not yet implemented")
    }

    override val effect: Flow<Nothing>
        get() = TODO("Not yet implemented")

    init {
        observeTimeline.execute(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            )
        )
    }
}
