package dev.mkeeda.spaceship.data.credential

data class LoginCredential(
    val domain: String,
    val username: String,
    val password: String,
)
