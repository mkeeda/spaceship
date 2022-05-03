package dev.mkeeda.spaceship.domain.repository

import dev.mkeeda.spaceship.data.credential.LoginCredential

interface LoginCredentialRepository {
    suspend fun save(newCredential: LoginCredential)
}
