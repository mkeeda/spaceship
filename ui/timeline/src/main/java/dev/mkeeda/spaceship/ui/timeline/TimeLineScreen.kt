package dev.mkeeda.spaceship.ui.timeline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.data.credential.NoLoginCredentialException
import dev.mkeeda.spaceship.ui.common.component.SpaceshipAppBar
import dev.mkeeda.spaceship.ui.common.util.PreviewBackground
import dev.mkeeda.spaceship.ui.common.util.UiCommonString
import dev.mkeeda.spaceship.ui.timeline.presentation.TimelineViewModel
import dev.mkeeda.spaceship.ui.timeline.presentation.fakeTimeline
import dev.mkeeda.spaceship.ui.timeline.presentation.fakeTimelinePostItems
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.toInstant

@Composable
fun TimelineScreen(
    openPostDetails: (TimelinePost) -> Unit,
    openLoginFlow: () -> Unit,
) {
    val viewModel: TimelineViewModel = hiltViewModel()
    val pagingTimelinePosts = viewModel.pagingTimeline.collectAsLazyPagingItems()
    val pagingState = pagingTimelinePosts.loadState.refresh
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = pagingState is LoadState.Loading
    )

    if (pagingState is LoadState.Error && pagingState.error is NoLoginCredentialException) {
        openLoginFlow()
    }

    TimelineScreen(
        pagingTimelinePosts = pagingTimelinePosts,
        swipeRefreshState = swipeRefreshState,
        openPostDetails = openPostDetails,
    )
}

@Composable
private fun TimelineScreen(
    pagingTimelinePosts: LazyPagingItems<TimelinePost>,
    swipeRefreshState: SwipeRefreshState,
    openPostDetails: (TimelinePost) -> Unit,
) {
    Scaffold(
        topBar = {
            SpaceshipAppBar(title = stringResource(id = UiCommonString.Timeline_AppBar_Title))
        }
    ) { contentPadding ->
        RefreshableTimeline(
            pagingTimelinePosts = pagingTimelinePosts,
            swipeRefreshState = swipeRefreshState,
            onTimelineRefresh = { pagingTimelinePosts.refresh() },
            openPostDetails = openPostDetails,
            contentPadding = contentPadding
        )
    }
}

@Composable
private fun RefreshableTimeline(
    pagingTimelinePosts: LazyPagingItems<TimelinePost>,
    swipeRefreshState: SwipeRefreshState,
    onTimelineRefresh: () -> Unit,
    openPostDetails: (TimelinePost) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onTimelineRefresh,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
            )
        }
    ) {
        Timeline(
            pagingTimelinePosts = pagingTimelinePosts,
            openPostDetails = openPostDetails,
            contentPadding = contentPadding
        )
    }
}

@Composable
private fun Timeline(
    pagingTimelinePosts: LazyPagingItems<TimelinePost>,
    openPostDetails: (TimelinePost) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = contentPadding
    ) {
        items(pagingTimelinePosts) { post ->
            TimelineRow(
                post = post!!, // not be null, because placeholder is disabled
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
            pagingTimelinePosts = flowOf(PagingData.from(fakeTimelinePostItems)).collectAsLazyPagingItems(),
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
            htmlBody = post.body
        )
    }
}

@Preview
@Composable
private fun PostRowPreview() {
    PreviewBackground {
        TimelineRow(
            post = fakeTimeline(
                senderName = "新垣結衣abcdefghigklnmop",
                postTime = "2021-08-22T14:21:00.000Z".toInstant(),
                body = "昔々あるところに、おじいさんとおばあさんがいました",
            ),
            onSelectPost = {}
        )
    }
}
