package dev.mkeeda.spaceship.infra.repositoryimpl

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.kintone.KintoneThread
import dev.mkeeda.spaceship.domain.repository.ThreadRepository
import dev.mkeeda.spaceship.infra.api.KintoneApiService
import dev.mkeeda.spaceship.infra.api.thread.ThreadPostList
import dev.mkeeda.spaceship.infra.api.thread.ThreadPostListResponse
import javax.inject.Inject

class ThreadRepositoryImpl @Inject constructor(
    private val kintoneApiService: KintoneApiService
) : ThreadRepository {
    override suspend fun getThreadPost(threadId: Long, postId: PostId): KintoneThread {
        /**
         * When it requests with size = 1,
         * the response is one thread post that includes children comment posts.
         */
        val response = kintoneApiService.post<ThreadPostListResponse>(
            endpoint = ThreadPostList,
            param = ThreadPostList.RequestParams(
                threadId = threadId,
                postIds = listOf(postId.value),
                size = 1
            )
        )
        return response.items.first()
    }
}
