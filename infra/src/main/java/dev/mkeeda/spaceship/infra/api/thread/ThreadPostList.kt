package dev.mkeeda.spaceship.infra.api.thread

import dev.mkeeda.spaceship.infra.api.KintoneApiEndpoint

object ThreadPostList : KintoneApiEndpoint {
    override val path: String = "/k/api/space/thread/post/list.json"

    data class RequestParams(
        val threadId: Long,
        val postIds: List<Long>,
        val size: Int = 1,
    ) : KintoneApiEndpoint.RequestParam
}
