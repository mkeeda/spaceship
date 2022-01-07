package dev.mkeeda.spaceship.infra.repositoryimpl

import dev.mkeeda.spaceship.data.kintone.KintoneThreadPost
import dev.mkeeda.spaceship.domain.repository.ThreadRepository
import dev.mkeeda.spaceship.infra.api.thread.ThreadPostList
import dev.mkeeda.spaceship.infra.api.thread.ThreadPostListService
import javax.inject.Inject

class ThreadRepositoryImpl @Inject constructor(
    private val threadPostListService: ThreadPostListService
) : ThreadRepository {
    override suspend fun getThreadPost(threadId: Long, postId: Long): KintoneThreadPost {
        /**
         * When it requests with size = 1,
         * the response is one thread post that includes children comment posts.
         */
        val response = threadPostListService.getThreadPostList(
            param = ThreadPostList.RequestParams(
                threadId = threadId,
                postIds = listOf(postId),
                size = 1
            )
        )
        return response.items.first()
    }
}
