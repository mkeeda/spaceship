package dev.mkeeda.spaceship.ui.login.password.presentation

data class PasswordLoginViewState(
    val clientCertFileName: String? = null
) {
    companion object {
        val Empty = PasswordLoginViewState()
    }
}
