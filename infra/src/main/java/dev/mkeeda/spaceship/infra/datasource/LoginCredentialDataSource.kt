package dev.mkeeda.spaceship.infra.datasource

import dev.mkeeda.spaceship.data.credential.LoginCredential
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class LoginCredentialDataSource @Inject constructor() {
    private var memoryCache: LoginCredential? = null

    fun writeLoginCredential(newCredential: LoginCredential) {
        memoryCache = newCredential
    }

    fun readLoginCredential(): LoginCredential? {
        return memoryCache
    }
}
