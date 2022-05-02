package dev.mkeeda.spaceship.infra.api

import android.util.Base64
import dev.mkeeda.spaceship.infra.datasource.LoginCredentialDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.serialization.Serializable

internal class KintoneApiService @Inject constructor(
    private val httpClient: HttpClient,
    private val loginCredentialDataSource: LoginCredentialDataSource
) {
    private val loginCredential = loginCredentialDataSource.getLoginCredential()

    private val authentication: String = loginCredential.let {
        Base64.encodeToString(
            "${it.username}:${it.password}".toByteArray(),
            Base64.URL_SAFE or Base64.NO_WRAP
        )
    }

    suspend inline fun <reified T : KintoneApiEndpoint.Response> post(
        endpoint: KintoneApiEndpoint,
        param: KintoneApiEndpoint.RequestParam? = null
    ): T {
        val response: SuccessResponse<T> = httpClient.post(loginCredential.domain + endpoint.path) {
            header("X-Cybozu-Authorization", authentication)
            contentType(ContentType.Application.Json)
            param?.let {
                setBody(it)
            }
        }.body()
        return response.result
    }
}

internal interface KintoneApiEndpoint {
    val path: String

    interface RequestParam

    interface Response
}

@Serializable
internal data class SuccessResponse<out T>(
    val success: Boolean = true,
    val result: T
)
