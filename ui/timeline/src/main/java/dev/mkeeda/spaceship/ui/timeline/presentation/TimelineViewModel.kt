package dev.mkeeda.spaceship.ui.timeline.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.domain.usecase.ObserveTimeline
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class TimelineViewModel @Inject constructor(
    observeTimeline: ObserveTimeline
) : ViewModel() {
    val pagingTimeline: Flow<PagingData<TimelinePost>> =
        observeTimeline.output.cachedIn(viewModelScope)

    init {
        observeTimeline.execute(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            )
        )
    }
}
