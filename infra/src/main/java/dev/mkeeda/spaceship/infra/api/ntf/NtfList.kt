package dev.mkeeda.spaceship.infra.api.ntf

import dev.mkeeda.spaceship.data.kintone.KintoneNotification
import dev.mkeeda.spaceship.data.kintone.KintoneUser
import dev.mkeeda.spaceship.infra.api.KintoneApiEndpoint
import dev.mkeeda.spaceship.infra.api.KintoneApiService
import kotlinx.serialization.Serializable
import javax.inject.Inject

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

interface NtfListService {
    suspend fun getNtfList(param: NtfList.RequestParam): NtfList.Response
}

internal class NtfListServiceImpl @Inject constructor(
    private val kintoneApiService: KintoneApiService
) : NtfListService {
    override suspend fun getNtfList(param: NtfList.RequestParam): NtfList.Response {
        return kintoneApiService.post(endpoint = NtfList, param = param)
    }
}
