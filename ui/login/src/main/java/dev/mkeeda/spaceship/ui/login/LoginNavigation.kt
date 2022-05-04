package dev.mkeeda.spaceship.ui.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.mkeeda.spaceship.ui.login.password.PasswordLoginScreen

sealed class LoginScreen(val route: String) {
    internal object PasswordLogin : LoginScreen(route = "password")

    companion object {
        const val Root = "login"
    }
}

fun NavGraphBuilder.loginGraph(openMainScreen: () -> Unit) {
    navigation(
        startDestination = LoginScreen.PasswordLogin.route,
        route = LoginScreen.Root
    ) {
        composable(route = LoginScreen.PasswordLogin.route) {
            PasswordLoginScreen(openMainScreen = openMainScreen)
        }
    }
}
