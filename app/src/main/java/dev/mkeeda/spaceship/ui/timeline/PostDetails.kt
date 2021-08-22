package dev.mkeeda.spaceship.ui.timeline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mkeeda.spaceship.data.TimelinePostDetails
import dev.mkeeda.spaceship.data.fakeTimeListDetails
import dev.mkeeda.spaceship.ui.theme.SpaceshipTheme

@Composable
fun PostDetailsScreen(postId: Int) {
    PostDetails(postDetails = fakeTimeListDetails(postId))
}

@Composable
fun PostDetails(postDetails: TimelinePostDetails) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            SenderIcon()
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = postDetails.senderName,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = postDetails.postTime,
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = postDetails.body)
    }
}

@Preview(showBackground = true)
@Composable
fun PostDetailsScreenPreview() {
    SpaceshipTheme {
        PostDetails(postDetails = fakeTimeListDetails(postId = 0))
    }
}
