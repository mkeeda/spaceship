package dev.mkeeda.spaceship.infra.api

import kotlinx.serialization.Serializable

object NtfList : KintoneApiEndpoint {
    override val path: String = "/k/api/ntf/list.json"

    @Serializable
    data class RequestParameter(
        val checkIgnoreMention: Boolean = true,
        val checkNew: Boolean?,
        val mentioned: Boolean? = null,
        val flagged: Boolean? = null,
        val size: Int = 50,
        val offset: Int = 0,
        val read: Boolean? = null,
        val filterId: Long? = null
    ) : KintoneApiEndpoint.RequestParameter
}

