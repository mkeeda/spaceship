package dev.mkeeda.spaceship.infra.datasource

import dev.mkeeda.spaceship.data.credential.LoginCredential
import dev.mkeeda.spaceship.infra.BuildConfig
import javax.inject.Inject

internal class LoginCredentialDataSource @Inject constructor() {
    private var memoryCache: LoginCredential? = null

    fun writeLoginCredential(newCredential: LoginCredential) {
        memoryCache = newCredential
    }

    fun readLoginCredential(): LoginCredential {
        return LoginCredential(
            domain = BuildConfig.kintoneDomain,
            username = BuildConfig.kintoneUsername,
            password = BuildConfig.kintonePassword
        )
    }
}
