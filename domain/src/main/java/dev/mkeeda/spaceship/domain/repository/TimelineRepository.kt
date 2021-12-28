package dev.mkeeda.spaceship.domain.repository

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.data.TimelinePostDetail

interface TimelineRepository {
    suspend fun getTimelinePostList(): List<TimelinePost>
    suspend fun getTimelinePost(postId: PostId): TimelinePostDetail
}
