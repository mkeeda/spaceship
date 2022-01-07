package dev.mkeeda.spaceship.infra.api.thread

import dev.mkeeda.spaceship.data.kintone.KintoneThreadPost
import dev.mkeeda.spaceship.infra.api.KintoneApiEndpoint
import dev.mkeeda.spaceship.infra.api.KintoneApiService
import javax.inject.Inject
import kotlinx.serialization.Serializable

object ThreadPostList : KintoneApiEndpoint {
    override val path: String = "/k/api/space/thread/post/list.json"

    @Serializable
    data class RequestParams(
        val threadId: Long,
        val postIds: List<Long>,
        val size: Int,
    ) : KintoneApiEndpoint.RequestParam

    @Serializable
    data class Response(
        val items: List<KintoneThreadPost>
    ) : KintoneApiEndpoint.Response
}

interface ThreadPostListService {
    suspend fun getThreadPostList(param: ThreadPostList.RequestParams): ThreadPostList.Response
}

internal class ThreadPostListServiceImpl @Inject constructor(
    private val kintoneApiService: KintoneApiService
) : ThreadPostListService {
    override suspend fun getThreadPostList(param: ThreadPostList.RequestParams): ThreadPostList.Response {
        return kintoneApiService.post(endpoint = ThreadPostList, param = param)
    }
}
