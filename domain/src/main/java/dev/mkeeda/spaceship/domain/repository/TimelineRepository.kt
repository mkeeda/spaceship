package dev.mkeeda.spaceship.domain.repository

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.TimelinePost

interface TimelineRepository {
    suspend fun getTimelinePostList(): List<TimelinePost>
    suspend fun getTimelinePost(postId: PostId): TimelinePost
}
