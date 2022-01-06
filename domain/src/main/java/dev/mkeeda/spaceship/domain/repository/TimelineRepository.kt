package dev.mkeeda.spaceship.domain.repository

import androidx.paging.PagingData
import dev.mkeeda.spaceship.data.TimelinePost
import kotlinx.coroutines.flow.Flow

interface TimelineRepository {
    suspend fun getTimelinePostList(): Flow<PagingData<TimelinePost>>
}
