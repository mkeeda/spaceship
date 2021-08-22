package dev.mkeeda.spaceship.ui.util

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import dev.mkeeda.spaceship.ui.theme.SpaceshipTheme

@Composable
fun PreviewBackground(content: @Composable () -> Unit) {
    SpaceshipTheme {
        Surface(content = content)
    }
}
