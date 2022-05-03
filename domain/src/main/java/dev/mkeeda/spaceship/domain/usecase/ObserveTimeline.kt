package dev.mkeeda.spaceship.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import dev.mkeeda.spaceship.domain.usecase.base.NoParamPagingUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveTimeline @Inject constructor(
    private val repository: TimelineRepository
) : NoParamPagingUseCase<TimelinePost>() {
    override fun useCaseFlow(config: PagingConfig): Flow<PagingData<TimelinePost>> {
        return repository.getPagingTimelineFlow(config)
    }
}
