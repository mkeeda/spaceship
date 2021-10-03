package dev.mkeeda.spaceship.infra.api

object ThreadPostList : KintoneApiEndpoint {
    override val path: String = "/k/api/space/thread/post/list.json"

    data class RequestParams(
        val threadId: Long,
        val postIds: List<Long>,
        val size: Int = 1,
    ) : KintoneApiEndpoint.RequestParam
}
