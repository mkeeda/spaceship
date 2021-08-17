package dev.mkeeda.spaceship.ui.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.mkeeda.spaceship.data.TimeLinePost
import dev.mkeeda.spaceship.data.fakeTimeLinePostItems
import dev.mkeeda.spaceship.ui.theme.Gray400
import dev.mkeeda.spaceship.ui.theme.SpaceshipTheme

@Composable
fun TimeLineScreen(postItems: List<TimeLinePost>) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "TimeLine")})
        },
    ) {
        TimeLine(postItems = postItems)
    }
}

@Preview(showBackground = true)
@Composable
fun TimeLineScreenPreview() {
    SpaceshipTheme {
        TimeLineScreen(postItems = fakeTimeLinePostItems)
    }
}

@Composable
fun TimeLine(postItems: List<TimeLinePost>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(postItems) { post ->
            TimeLineRow(post = post)
        }
    }
}

@Composable
fun TimeLineRow(post: TimeLinePost) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // TODO: Sender icon
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Gray400) // FIXME: use theme color
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = post.senderName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = post.postTime,
                    maxLines = 1,
                    textAlign = TextAlign.End,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = post.body)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostRowPreview() {
    SpaceshipTheme {
        TimeLineRow(
            post = TimeLinePost(
                senderName = "新垣結衣abcdefghigklnmop",
                postTime = "2021/08/16 14:26",
                body = "昔々あるところに、おじいさんとおばあさんがいました",
            )
        )
    }
}
