package dev.mkeeda.spaceship.ui.timeline.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.domain.usecase.ObserveTimeline
import dev.mkeeda.spaceship.domain.usecase.Success
import dev.mkeeda.spaceship.ui.common.dataflow.Presentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val observeTimeline: ObserveTimeline
) : ViewModel(), Presentation<TimelineViewState, Nothing, Nothing> {

    override val state: StateFlow<TimelineViewState> = observeTimeline.output
        .filterIsInstance<Success<List<TimelinePost>>>()
        .map { success ->
            TimelineViewState(
                postItems = success.data
            )
        }.stateIn(
            initialValue = TimelineViewState.Initial,
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
            observeTimeline.execute()
        }
    }
}
