package dev.mkeeda.spaceship.infra.api.ntf

import dev.mkeeda.spaceship.data.kintone.KintoneNotification
import dev.mkeeda.spaceship.data.kintone.KintoneUser
import dev.mkeeda.spaceship.infra.api.KintoneApiEndpoint
import kotlinx.serialization.Serializable

object NtfList : KintoneApiEndpoint {
    override val path: String = "/k/api/ntf/list.json"

    @Serializable
    data class RequestParam(
        val checkIgnoreMention: Boolean = false,
        val checkNew: Boolean? = false,
        val mentioned: Boolean? = null,
        val flagged: Boolean? = null,
        val size: Int = 50,
        val offset: Int = 0,
        val read: Boolean? = null,
        val filterId: Long? = null
    ) : KintoneApiEndpoint.RequestParam

    @Serializable
    data class Response(
        val hasMore: Boolean,
        val ntf: List<KintoneNotification>,
        val senders: Map<Long, KintoneUser>,
        val hasIgnoreMention: Boolean?
    ) : KintoneApiEndpoint.Response
}