package dev.mkeeda.spaceship.infra.datasource

import dev.mkeeda.spaceship.data.credential.LoginCredential
import dev.mkeeda.spaceship.infra.BuildConfig
import javax.inject.Inject

internal class LoginCredentialDataSource @Inject constructor() {
    fun getLoginCredential(): LoginCredential {
        return LoginCredential(
            domain = BuildConfig.kintoneDomain,
            username = BuildConfig.kintoneUsername,
            password = BuildConfig.kintonePassword
        )
    }
}
