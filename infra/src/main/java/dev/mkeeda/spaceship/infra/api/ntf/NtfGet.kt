package dev.mkeeda.spaceship.infra.api.ntf

import dev.mkeeda.spaceship.data.kintone.KintoneNotification
import dev.mkeeda.spaceship.data.kintone.KintoneUser
import dev.mkeeda.spaceship.infra.api.KintoneApiEndpoint
import kotlinx.serialization.Serializable

object NtfGet : KintoneApiEndpoint {
    override val path: String = "/k/api/ntf/get.json"

    @Serializable
    data class RequestParam(
        val id: Long
    ) : KintoneApiEndpoint.RequestParam
}

@Serializable
internal data class NtfGetResponse(
    val item: KintoneNotification,
    val sender: KintoneUser?
)
