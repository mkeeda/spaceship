package dev.mkeeda.spaceship.infra.api

import android.util.Base64
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class KintoneApiService @Inject constructor(
    val httpClient: HttpClient
) {
    val baseUrl = ""
    private val username = ""
    private val password = ""

    val authentication: String = Base64.encodeToString(
        "$username:$password".toByteArray(),
        Base64.URL_SAFE or Base64.NO_WRAP
    )

    suspend inline fun <reified T> post(
        endpoint: KintoneApiEndpoint,
        block: HttpRequestBuilder.() -> Unit = {}
    ): T = httpClient.post(baseUrl + endpoint.path) {
        header("X-Cybozu-Authorization", authentication)
        contentType(ContentType.Application.Json)
        block()
    }
}

interface KintoneApiEndpoint {
    val path: String
}
