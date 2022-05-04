package dev.mkeeda.spaceship.data.credential

import kotlinx.serialization.Serializable

@Serializable
data class LoginCredential(
    val domain: String,
    val username: String,
    val password: String,
)
