package dev.mkeeda.spaceship.ui.timeline

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.mkeeda.spaceship.data.TimeLinePostDetails
import dev.mkeeda.spaceship.data.fakeTimeListDetails

@Composable
fun PostDetailsScreen(postId: Int) {
    PostDetailsScreen(postDetails = fakeTimeListDetails(postId))
}

@Composable
fun PostDetailsScreen(postDetails: TimeLinePostDetails) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "PostDetails")})
        },
    ) {
        Column {
            Text(text = postDetails.id.toString())
            Text(text = postDetails.senderName)
            Text(text = postDetails.postTime)
            Text(text = postDetails.body)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostDetailsScreenPreview() {
    PostDetailsScreen(postDetails = fakeTimeListDetails(postId = 0))
}
