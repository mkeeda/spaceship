package dev.mkeeda.spaceship.ui.timeline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.data.longFakeTimelinePostItems
import dev.mkeeda.spaceship.ui.util.PreviewBackground

@Composable
fun TimelineScreen(openPostDetails: (TimelinePost) -> Unit) {
    Timeline(
        postItems = longFakeTimelinePostItems,
        openPostDetails = openPostDetails
    )
}

@Composable
fun Timeline(
    postItems: List<TimelinePost>,
    openPostDetails: (TimelinePost) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(postItems) { post ->
            TimelineRow(
                post = post,
                onSelectPost = openPostDetails
            )
        }
    }
}

@Preview
@Composable
fun TimelineScreenPreview() {
    PreviewBackground {
        Timeline(
            postItems = longFakeTimelinePostItems,
            openPostDetails = {}
        )
    }
}


@Composable
fun TimelineRow(
    post: TimelinePost,
    onSelectPost: (TimelinePost) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectPost(post) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SenderIcon(
            modifier = Modifier
                .align(Alignment.Top)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = post.senderName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = post.postTime,
                    maxLines = 1,
                    textAlign = TextAlign.End,
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.body)
        }
    }
}

@Preview
@Composable
fun PostRowPreview() {
    PreviewBackground {
        TimelineRow(
            post = TimelinePost(
                id = 0,
                senderName = "新垣結衣abcdefghigklnmop",
                postTime = "2021/08/16 14:26",
                body = "昔々あるところに、おじいさんとおばあさんがいました",
            ),
            onSelectPost = {}
        )
    }
}
