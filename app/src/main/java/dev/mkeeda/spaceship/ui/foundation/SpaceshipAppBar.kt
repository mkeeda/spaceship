package dev.mkeeda.spaceship.ui.foundation

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun SpaceshipAppBar(currentScreen: Screen) {
    TopAppBar(title = { Text(text = currentScreen.name) })
}
