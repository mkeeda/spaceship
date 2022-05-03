package dev.mkeeda.spaceship.ui.login.presentation

import dev.mkeeda.spaceship.data.credential.LoginCredential

sealed class PasswordLoginEvent {
    data class Submit(val loginCredential: LoginCredential): PasswordLoginEvent()
}
