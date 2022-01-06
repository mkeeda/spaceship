package dev.mkeeda.spaceship.domain.usecase

import androidx.paging.PagingData
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveTimeline @Inject constructor(
    private val repository: TimelineRepository
) {
    fun useCaseFlow(): Flow<PagingData<TimelinePost>> {
        return repository.getTimelinePostList()
    }
}
