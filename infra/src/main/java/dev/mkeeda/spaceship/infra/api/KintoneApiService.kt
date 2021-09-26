package dev.mkeeda.spaceship.infra.api

import android.util.Base64
import dev.mkeeda.spaceship.infra.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.serialization.Serializable

class KintoneApiService @Inject constructor(
    val httpClient: HttpClient
) {
    val baseUrl = BuildConfig.kintoneDomain
    private val username = BuildConfig.kintoneUsername
    private val password = BuildConfig.kintonePassword

    val authentication: String = Base64.encodeToString(
        "$username:$password".toByteArray(),
        Base64.URL_SAFE or Base64.NO_WRAP
    )

    suspend inline fun <reified T> post(
        endpoint: KintoneApiEndpoint,
        param: KintoneApiEndpoint.RequestParam? = null
    ): T {
        val response: SuccessResponse<T> = httpClient.post(baseUrl + endpoint.path) {
            header("X-Cybozu-Authorization", authentication)
            contentType(ContentType.Application.Json)
            param?.let {
                body = it
            }
        }
        return response.result
    }
}

interface KintoneApiEndpoint {
    val path: String

    interface RequestParam
}

@Serializable
data class SuccessResponse<out T>(
    val success: Boolean = true,
    val result: T
)
