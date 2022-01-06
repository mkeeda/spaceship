package dev.mkeeda.spaceship.domain.repository

import dev.mkeeda.spaceship.data.TimelinePost

interface TimelineRepository {
    suspend fun getTimelinePostList(): List<TimelinePost>
}
