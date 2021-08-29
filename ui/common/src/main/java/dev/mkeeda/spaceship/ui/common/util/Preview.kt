package dev.mkeeda.spaceship.ui.common.util

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import dev.mkeeda.spaceship.ui.common.theme.SpaceshipTheme

@Composable
fun PreviewBackground(content: @Composable () -> Unit) {
    SpaceshipTheme {
        Surface(content = content)
    }
}
