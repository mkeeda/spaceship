package dev.mkeeda.spaceship.ui.timeline.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.mkeeda.spaceship.ui.common.dataflow.Presentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class TimelineViewModel : ViewModel(), Presentation<TimelineViewState, Nothing, Nothing> {
    override val state: StateFlow<TimelineViewState> = flow<TimelineViewState> {
    }.stateIn(
        initialValue = TimelineViewState.longFake,
        scope = viewModelScope,
        started = SharingStarted.Lazily
    )

    override fun event(event: Nothing) {
        TODO("Not yet implemented")
    }

    override val effect: Flow<Nothing>
        get() = TODO("Not yet implemented")
}
