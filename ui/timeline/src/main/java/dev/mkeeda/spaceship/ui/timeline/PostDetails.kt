package dev.mkeeda.spaceship.ui.timeline

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import dev.mkeeda.spaceship.ui.common.util.PreviewBackground
import dev.mkeeda.spaceship.ui.timeline.presentation.CommentPost
import dev.mkeeda.spaceship.ui.timeline.presentation.FocusedPost
import dev.mkeeda.spaceship.ui.timeline.presentation.PostDetailsViewModel
import dev.mkeeda.spaceship.ui.timeline.presentation.PostDetailsViewState
import dev.mkeeda.spaceship.ui.timeline.presentation.ThreadPost
import dev.mkeeda.spaceship.ui.timeline.presentation.postDetailsViewModel

@Composable
fun PostDetailsScreen(postId: Long) {
    PostDetailsScreen(viewModel = postDetailsViewModel(postId))
}

@Composable
private fun PostDetailsScreen(
    viewModel: PostDetailsViewModel
) {
    val postDetailsViewState by viewModel.state.collectAsState()
    PostDetails(threadPostItems = postDetailsViewState.threadPostItems)
}

/**
 * @param threadPostItems includes one [FocusedPost] and some [CommentPost].
 * [threadPostItems] is sorted by post time
 */
@Composable
fun PostDetails(threadPostItems: List<ThreadPost>) {
    val focusedPostIndex = threadPostItems.indexOfFirst { it is FocusedPost }
    LazyColumn(
        state = rememberLazyListState(initialFirstVisibleItemIndex = focusedPostIndex),
        modifier = Modifier.fillMaxSize(),
        contentPadding = rememberInsetsPaddingValues(insets = LocalWindowInsets.current.navigationBars)
    ) {
        itemsIndexed(threadPostItems) { index, threadPost ->
            val linkToBeforeEnabled = index >= 1
            val linkToAfterEnabled = index < threadPostItems.size - 1
            when (threadPost) {
                is FocusedPost -> {
                    FocusedPostRow(
                        focusedPost = threadPost,
                        linkToBefore = linkToBeforeEnabled
                    )
                }
                is CommentPost -> {
                    CommentPostRow(
                        commentPost = threadPost,
                        linkToBefore = linkToBeforeEnabled,
                        linkToAfter = linkToAfterEnabled
                    )
                }
            }
        }
    }
}

@Composable
fun FocusedPostRow(
    focusedPost: FocusedPost,
    linkToBefore: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        FocusedPostContent(
            focusedPost,
            linkToBefore
        )
        Divider(modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun FocusedPostContent(
    focusedPost: FocusedPost,
    linkToBefore: Boolean = false
) {
    ConstraintLayout(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        val (senderIcon, postLinker, postHeaderText, body) = createRefs()
        createHorizontalChain(
            senderIcon,
            postHeaderText,
            chainStyle = ChainStyle.Packed(bias = 0.0f)
        )

        if (linkToBefore) {
            PostLinker(
                modifier = Modifier
                    .constrainAs(postLinker) {
                        height = Dimension.value(16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(senderIcon.top)
                        centerHorizontallyTo(senderIcon)
                    },
            )
        }
        SenderIcon(
            modifier = Modifier.constrainAs(senderIcon) {
                if (linkToBefore) {
                    top.linkTo(postLinker.bottom)
                } else {
                    top.linkTo(parent.top, margin = 16.dp)
                }
                start.linkTo(parent.start)
                end.linkTo(postHeaderText.start)
            }
        )

        Column(
            modifier = Modifier
                .constrainAs(postHeaderText) {
                    top.linkTo(senderIcon.top)
                    bottom.linkTo(body.top)
                    start.linkTo(senderIcon.end)
                    end.linkTo(parent.end)
                }
                .padding(start = 8.dp)
        ) {
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
        Text(
            modifier = Modifier.constrainAs(body) {
                top.linkTo(postHeaderText.bottom, margin = 8.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = focusedPost.body
        )
    }
}

@Composable
fun CommentPostRow(
    commentPost: CommentPost,
    linkToBefore: Boolean = false,
    linkToAfter: Boolean = false
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        val (beforePostLinker, afterPostLinker, senderIcon, postContent) = createRefs()

        if (linkToBefore) {
            PostLinker(
                modifier = Modifier
                    .constrainAs(beforePostLinker) {
                        height = Dimension.value(16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(senderIcon.top)
                        centerHorizontallyTo(senderIcon)
                    },
            )
        }
        SenderIcon(
            modifier = Modifier.constrainAs(senderIcon) {
                if (linkToBefore) {
                    top.linkTo(beforePostLinker.bottom)
                } else {
                    top.linkTo(parent.top, margin = 16.dp)
                }
                start.linkTo(parent.start)
            }
        )
        if (linkToAfter) {
            PostLinker(
                modifier = Modifier.constrainAs(afterPostLinker) {
                    height = Dimension.fillToConstraints
                    top.linkTo(senderIcon.bottom)
                    bottom.linkTo(parent.bottom)
                    centerHorizontallyTo(senderIcon)
                },
            )
        }
        PostContent(
            senderName = commentPost.senderName,
            postTime = commentPost.postTime,
            body = commentPost.body,
            modifier = Modifier.constrainAs(postContent) {
                width = Dimension.fillToConstraints
                top.linkTo(senderIcon.top)
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(senderIcon.end, margin = 8.dp)
                end.linkTo(parent.end)
            },
        )
    }
}

@Composable
fun PostLinker(
    modifier: Modifier = Modifier,
) {
    Divider(
        modifier = modifier
            .fillMaxHeight()
            .width(2.dp)
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun LightPostDetailsScreenPreview() {
    PreviewBackground {
        PostDetails(threadPostItems = PostDetailsViewState.fake.threadPostItems)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun DarkPostDetailsScreenPreview() {
    PreviewBackground {
        PostDetails(threadPostItems = PostDetailsViewState.fake.threadPostItems)
    }
}
