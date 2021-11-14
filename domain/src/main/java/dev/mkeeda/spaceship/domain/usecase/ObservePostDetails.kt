package dev.mkeeda.spaceship.domain.usecase

import dev.mkeeda.spaceship.data.Conversation
import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.PostingLocation
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.domain.repository.ThreadRepository
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ObservePostDetails @Inject constructor(
    private val timelineRepository: TimelineRepository,
    private val threadRepository: ThreadRepository
) : UseCase<PostId, Conversation>() {
    override fun useCaseFlow(param: PostId): Flow<Conversation> {
        return flow {
            val topic = timelineRepository.getTimelinePost(postId = param)
            val postItems = when (val location = topic.location) {
                is PostingLocation.App -> TODO("not implemented")
                PostingLocation.Message -> TODO("not implemented")
                is PostingLocation.People -> TODO("not implemented")
                is PostingLocation.Space -> spacePostItems(location)
            }
            emit(
                Conversation(
                    topicId = param,
                    comments = postItems.sorted()
                )
            )
        }
    }

    private suspend fun spacePostItems(spaceLocation: PostingLocation.Space): List<TimelinePost> {
        val threadPost = threadRepository.getThreadPost(
            threadId = spaceLocation.threadId,
            postId = spaceLocation.commentId
        )
        return listOf(
            TimelinePost(
                id = PostId(value = threadPost.id),
                senderName = threadPost.creator.name,
                postTime = threadPost.createdAt,
                body = threadPost.body,
                location = spaceLocation
            )
        ) + threadPost.comments.map { comment ->
            TimelinePost(
                id = PostId(value = comment.id),
                senderName = comment.creator.name,
                postTime = comment.createdAt,
                body = comment.body,
                location = PostingLocation.Space(
                    threadId = spaceLocation.threadId,
                    commentId = comment.id
                )
            )
        }
    }
}
