package dev.mkeeda.spaceship.ui.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Gray400,
    primaryVariant = Gray900,
    secondary = Yellow200
)

private val LightColorPalette = lightColors(
    primary = Gray800,
    primaryVariant = Gray900,
    secondary = Yellow400
)

@Composable
fun SpaceshipTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
