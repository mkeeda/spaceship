package dev.mkeeda.spaceship.infra.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import dev.mkeeda.spaceship.infra.api.KintoneApiService
import dev.mkeeda.spaceship.infra.datasource.TimelinePagingSource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TimelineRepositoryImpl @Inject constructor(
    private val kintoneApiService: KintoneApiService
) : TimelineRepository {
    override suspend fun getTimelinePostList(): Flow<PagingData<TimelinePost>> {
        return Pager(
            config = PagingConfig(
                pageSize = REQUEST_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { TimelinePagingSource(kintoneApiService) }
        ).flow
    }

    companion object {
        internal const val REQUEST_SIZE: Int = 30
    }
}
