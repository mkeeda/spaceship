package dev.mkeeda.spaceship.ui.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mkeeda.spaceship.ui.theme.Gray400

@Composable
fun SenderIcon(modifier: Modifier = Modifier) {
    // TODO: Use profile image
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(Gray400) // FIXME: use theme color
            .border(width = 2.dp, color = MaterialTheme.colors.surface, shape = CircleShape)
    )
}

@Preview
@Composable
private fun Preview() {
    SenderIcon()
}
