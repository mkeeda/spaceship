package dev.mkeeda.spaceship.ui.login.password.presentation

import android.net.Uri

sealed class PasswordLoginEvent {
    data class Submit(val formState: LoginFormState) : PasswordLoginEvent()
}

data class LoginFormState(
    val loginOrigin: String,
    val username: String,
    val password: String,
    val secureAccessFormState: SecureAccessFormState? = null
)

data class SecureAccessFormState(
    val clientCertFileUri: Uri,
    val clientCertPassword: String
)
