package dev.mkeeda.spaceship.domain.usecase

import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ObserveTimeline @Inject constructor(
    private val repository: TimelineRepository
) : NoParamUseCase<List<TimelinePost>>() {

    override fun useCaseFlow(): Flow<List<TimelinePost>> {
        return flow {
            val timelinePostItems = repository.getTimelinePostList()
            emit(timelinePostItems)
        }
    }
}
