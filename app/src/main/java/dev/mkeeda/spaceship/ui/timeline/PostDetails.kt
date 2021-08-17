package dev.mkeeda.spaceship.ui.timeline

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.mkeeda.spaceship.data.TimeLinePost
import dev.mkeeda.spaceship.data.fakeTimeLinePostItems

@Composable
fun PostDetailsScreen(post: TimeLinePost) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "PostDetails")})
        },
    ) {
        Column {
            Text(text = post.senderName)
            Text(text = post.postTime)
            Text(text = post.body)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostDetailsScreenPreview() {
    PostDetailsScreen(post = fakeTimeLinePostItems.first())
}
