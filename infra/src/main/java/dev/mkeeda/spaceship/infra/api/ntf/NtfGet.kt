package dev.mkeeda.spaceship.infra.api.ntf

import dev.mkeeda.spaceship.data.kintone.KintoneNotification
import dev.mkeeda.spaceship.data.kintone.KintoneUser
import dev.mkeeda.spaceship.infra.api.KintoneApiEndpoint
import dev.mkeeda.spaceship.infra.api.KintoneApiServiceBuilder
import javax.inject.Inject
import kotlinx.serialization.Serializable

object NtfGet : KintoneApiEndpoint {
    override val path: String = "/k/api/ntf/get.json"

    @Serializable
    data class RequestParam(
        val id: Long
    ) : KintoneApiEndpoint.RequestParam

    @Serializable
    data class Response(
        val item: KintoneNotification,
        val sender: KintoneUser?
    ) : KintoneApiEndpoint.Response
}

interface NtfGetService {
    suspend fun getNtfGet(param: NtfGet.RequestParam): NtfGet.Response
}

internal class NtfGetServiceImpl @Inject constructor(
    private val kintoneApiServiceBuilder: KintoneApiServiceBuilder
) : NtfGetService {
    override suspend fun getNtfGet(param: NtfGet.RequestParam): NtfGet.Response {
        val kintoneApiService = kintoneApiServiceBuilder.build()
        return kintoneApiService.post(endpoint = NtfGet, param = param)
    }
}
