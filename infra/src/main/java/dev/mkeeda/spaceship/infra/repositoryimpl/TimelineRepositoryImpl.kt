package dev.mkeeda.spaceship.infra.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import dev.mkeeda.spaceship.infra.api.ntf.NtfListService
import dev.mkeeda.spaceship.infra.datasource.TimelinePagingSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TimelineRepositoryImpl @Inject constructor(
    private val ntfListService: NtfListService
) : TimelineRepository {
    override fun getPagingTimelineFlow(config: PagingConfig): Flow<PagingData<TimelinePost>> {
        return Pager(
            config = config,
            pagingSourceFactory = {
                TimelinePagingSource(
                    networkRequestSize = config.pageSize,
                    ntfListService = ntfListService
                )
            }
        ).flow
    }
}
