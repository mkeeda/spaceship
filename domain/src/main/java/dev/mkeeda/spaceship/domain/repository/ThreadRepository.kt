package dev.mkeeda.spaceship.domain.repository

import dev.mkeeda.spaceship.data.kintone.KintoneThreadPost

interface ThreadRepository {
    suspend fun getThreadPost(threadId: Long, postId: Long): KintoneThreadPost
}
