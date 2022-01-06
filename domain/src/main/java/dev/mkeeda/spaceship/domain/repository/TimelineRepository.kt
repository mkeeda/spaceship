package dev.mkeeda.spaceship.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.mkeeda.spaceship.data.TimelinePost
import kotlinx.coroutines.flow.Flow

interface TimelineRepository {
    fun getPagingTimelineFlow(config: PagingConfig): Flow<PagingData<TimelinePost>>
}
