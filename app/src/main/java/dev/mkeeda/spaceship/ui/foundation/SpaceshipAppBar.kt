package dev.mkeeda.spaceship.ui.foundation

import android.content.res.Configuration
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.ui.TopAppBar
import dev.mkeeda.spaceship.ui.common.theme.SpaceshipTheme

@Composable
fun SpaceshipAppBar(currentScreen: Screen) {
    TopAppBar(
        title = { Text(text = currentScreen.name) },
        backgroundColor = MaterialTheme.colors.surface,
        contentPadding = WindowInsets.statusBars.asPaddingValues(),
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun LightAppBarPreview() {
    SpaceshipTheme {
        SpaceshipAppBar(currentScreen = Screen.Timeline)
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun DarkAppBarPreview() {
    SpaceshipTheme {
        SpaceshipAppBar(currentScreen = Screen.Timeline)
    }
}
