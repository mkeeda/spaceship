package dev.mkeeda.spaceship.infra.api.thread

import dev.mkeeda.spaceship.data.kintone.KintoneThreadPost
import dev.mkeeda.spaceship.infra.api.KintoneApiEndpoint
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
