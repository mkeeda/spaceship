package dev.mkeeda.spaceship.domain.repository

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.TimelinePostDetail

interface PostDetailsRepository {
    suspend fun getPostDetail(postId: PostId): TimelinePostDetail
}
