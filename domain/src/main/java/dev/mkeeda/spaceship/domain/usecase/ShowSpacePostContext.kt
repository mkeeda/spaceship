package dev.mkeeda.spaceship.domain.usecase

import dev.mkeeda.spaceship.data.PostingLocation
import dev.mkeeda.spaceship.data.TimelinePostDetail
import dev.mkeeda.spaceship.domain.repository.ThreadRepository
import dev.mkeeda.spaceship.domain.usecase.base.UseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShowSpacePostContext @Inject constructor(
    private val threadRepository: ThreadRepository
) : UseCase<PostingLocation.Space, List<TimelinePostDetail>>() {
    override fun useCaseFlow(param: PostingLocation.Space): Flow<List<TimelinePostDetail>> {
        return flow {
            val threadPost = threadRepository.getThreadPost(
                threadId = param.threadId,
                postId = param.commentId
            )
            val postOrigin = TimelinePostDetail(
                senderName = threadPost.creator.name,
                postTime = threadPost.createdAt,
                htmlBody = threadPost.body,
                location = PostingLocation.Space(
                    threadId = threadPost.threadId,
                    commentId = threadPost.id,
                    commentReplyId = null
                )
            )
            val postContext = threadPost.comments.map { comment ->
                TimelinePostDetail(
                    senderName = comment.creator.name,
                    postTime = comment.createdAt,
                    htmlBody = comment.body,
                    location = PostingLocation.Space(
                        threadId = threadPost.threadId,
                        commentId = comment.postId,
                        commentReplyId = comment.id
                    )
                )
            }
            val spacePosts = listOf(postOrigin) + postContext
            emit(spacePosts.sorted())
        }
    }
}
