package dev.mkeeda.spaceship.ui.timeline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun PostContent(
    senderName: String,
    postTime: String,
    body: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = senderName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = postTime,
                maxLines = 1,
                textAlign = TextAlign.End,
                modifier = Modifier.alpha(ContentAlpha.medium)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = body)
    }
}
