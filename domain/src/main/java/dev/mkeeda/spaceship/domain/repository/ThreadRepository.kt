package dev.mkeeda.spaceship.domain.repository

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.kintone.KintoneThread

interface ThreadRepository {
    suspend fun getThreadPost(threadId: Long, postId: PostId): KintoneThread
}
