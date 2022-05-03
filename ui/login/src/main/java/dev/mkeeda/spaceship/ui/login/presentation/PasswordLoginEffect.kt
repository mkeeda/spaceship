package dev.mkeeda.spaceship.ui.login.presentation

sealed class PasswordLoginEffect {
    object NavigateToMain : PasswordLoginEffect()
}
