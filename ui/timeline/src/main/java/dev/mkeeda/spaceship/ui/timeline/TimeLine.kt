package dev.mkeeda.spaceship.ui.timeline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import dev.mkeeda.spaceship.ui.common.util.PreviewBackground
import dev.mkeeda.spaceship.ui.timeline.presentation.TimelinePost
import dev.mkeeda.spaceship.ui.timeline.presentation.TimelineViewModel
import dev.mkeeda.spaceship.ui.timeline.presentation.TimelineViewState

@Composable
fun TimelineScreen(openPostDetails: (TimelinePost) -> Unit) {
    TimelineScreen(
        viewModel = viewModel(),
        openPostDetails = openPostDetails
    )
}

@Composable
private fun TimelineScreen(
    viewModel: TimelineViewModel,
    openPostDetails: (TimelinePost) -> Unit
) {
    val timelineViewState by viewModel.state.collectAsState()
    Timeline(
        viewState = timelineViewState,
        openPostDetails = openPostDetails
    )
}

@Composable
private fun Timeline(
    viewState: TimelineViewState,
    openPostDetails: (TimelinePost) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.navigationBars)
    ) {
        items(viewState.postItems) { post ->
            TimelineRow(
                post = post,
                onSelectPost = openPostDetails
            )
        }
    }
}

@Preview
@Composable
private fun TimelineScreenPreview() {
    PreviewBackground {
        Timeline(
            viewState = TimelineViewState.longFake,
            openPostDetails = {}
        )
    }
}


@Composable
private fun TimelineRow(
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
        PostContent(
            senderName = post.senderName,
            postTime = post.postTime,
            body = post.body
        )
    }
}

@Preview
@Composable
private fun PostRowPreview() {
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
