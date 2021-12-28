package dev.mkeeda.spaceship.domain.usecase

import dev.mkeeda.spaceship.data.PostingLocation
import dev.mkeeda.spaceship.data.TimelinePostDetail
import dev.mkeeda.spaceship.domain.repository.ThreadRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShowSpacePostContext @Inject constructor(
    private val threadRepository: ThreadRepository
) : UseCase<PostingLocation.Space, List<TimelinePostDetail>>() {
    override fun useCaseFlow(param: PostingLocation.Space): Flow<List<TimelinePostDetail>> {
        return flow {
            val threadPost = threadRepository.getThreadPost(
                threadId = param.threadId,
                postId = param.commentId
            )
            val postContext = threadPost.comments.map { comment ->
                TimelinePostDetail(
                    senderName = comment.creator.name,
                    postTime = comment.createdAt,
                    body = comment.body,
                    location = PostingLocation.Space(
                        threadId = param.threadId,
                        commentId = comment.id
                    )
                )
            }
            emit(postContext)
        }
    }
}
