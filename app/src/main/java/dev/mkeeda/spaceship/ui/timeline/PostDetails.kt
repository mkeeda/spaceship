package dev.mkeeda.spaceship.ui.timeline

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
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
    LazyColumn(Modifier.fillMaxSize()) {
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
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        ConstraintLayout {
            val (senderIcon, postLinker, postInfo, body) = createRefs()
            createHorizontalChain(senderIcon, postInfo, chainStyle = ChainStyle.Packed(bias = 0.0f))

            PostLinker(
                modifier = Modifier
                    .height(16.dp)
                    .constrainAs(postLinker) {
                        bottom.linkTo(senderIcon.top)
                        centerHorizontallyTo(senderIcon)
                    },
                bottomPadding = 2.dp
            )
            SenderIcon(
                modifier = Modifier.constrainAs(senderIcon) {
                    top.linkTo(postLinker.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(postInfo.start)
                }
            )

            Column(
                modifier = Modifier.constrainAs(postInfo) {
                    top.linkTo(postLinker.bottom)
                    bottom.linkTo(body.top)
                    start.linkTo(senderIcon.end)
                    end.linkTo(parent.end)
                }.padding(start = 8.dp)
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
                    top.linkTo(postInfo.bottom, margin = 8.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = focusedPost.body
            )
        }
    }
}

@Composable
fun CommentPostRow(
    commentPost: CommentPost,
    beforeLinkTo: Boolean = false,
    afterLinkTo: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SenderIcon()
            PostLinker(topPadding = 2.dp)
        }
        Spacer(modifier = Modifier.width(8.dp))
        PostContent(
            senderName = commentPost.senderName,
            postTime = commentPost.postTime,
            body = commentPost.body,
            Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun PostLinker(
    modifier: Modifier = Modifier,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
) {
    Divider(
        modifier = modifier
            .fillMaxHeight()
            .width(2.dp)
            .padding(top = topPadding, bottom = bottomPadding)
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun LightPostDetailsScreenPreview() {
    PreviewBackground {
        PostDetails(threadPosts = fakeThreadPosts(postId = 0))
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun DarkPostDetailsScreenPreview() {
    PreviewBackground {
        PostDetails(threadPosts = fakeThreadPosts(postId = 0))
    }
}
