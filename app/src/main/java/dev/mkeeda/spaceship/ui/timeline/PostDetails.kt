package dev.mkeeda.spaceship.ui.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mkeeda.spaceship.data.CommentPost
import dev.mkeeda.spaceship.data.FocusedPost
import dev.mkeeda.spaceship.data.ThreadPost
import dev.mkeeda.spaceship.data.fakeThreadPosts
import dev.mkeeda.spaceship.ui.util.PreviewBackground

@Composable
fun PostDetailsScreen(postId: Int) {
    PostDetails(threadPosts = fakeThreadPosts(postId))
}

@Composable
fun PostDetails(threadPosts: List<ThreadPost>) {
    LazyColumn {
        items(threadPosts) { threadPost ->
            when (threadPost) {
                is FocusedPost -> {
                    FocusedPostRow(focusedPost = threadPost)
                }
                is CommentPost -> {
                    CommentPostRow(commentPost = threadPost)
                }
            }
        }
    }
}

@Composable
fun FocusedPostRow(focusedPost: FocusedPost) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            SenderIcon()
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = focusedPost.senderName,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = focusedPost.postTime,
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = focusedPost.body)
    }
}

@Composable
fun CommentPostRow(commentPost: CommentPost) {
    Column(
        modifier = Modifier
            .background(Color.Red)
            .padding(16.dp)
    ) {
        Row {
            SenderIcon()
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = commentPost.senderName,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = commentPost.postTime,
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = commentPost.body)
    }
}

@Preview
@Composable
fun PostDetailsScreenPreview() {
    PreviewBackground {
        PostDetails(threadPosts = fakeThreadPosts(postId = 0))
    }
}
