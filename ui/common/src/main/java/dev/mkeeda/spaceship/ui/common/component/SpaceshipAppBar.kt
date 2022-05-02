package dev.mkeeda.spaceship.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.google.accompanist.insets.ui.TopAppBar
import dev.mkeeda.spaceship.ui.common.theme.SpaceshipTheme

@Composable
fun SpaceshipAppBar(
    title: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = WindowInsets.statusBars.asPaddingValues(),
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentPadding = contentPadding,
        navigationIcon = navigationIcon,
        actions = actions,
        contentColor = contentColor,
        elevation = elevation,
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppBarPreview() {
    SpaceshipTheme {
        SpaceshipAppBar(title = "Screen Title")
    }
}
