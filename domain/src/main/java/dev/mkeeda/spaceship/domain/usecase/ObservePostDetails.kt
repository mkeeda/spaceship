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
            val conversation = when (val location = topic.location) {
                is PostingLocation.App -> TODO("not implemented")
                PostingLocation.Message -> TODO("not implemented")
                is PostingLocation.People -> TODO("not implemented")
                is PostingLocation.Space -> spaceConversation(location)
            }
            emit(conversation)
        }
    }

    private suspend fun spaceConversation(spaceLocation: PostingLocation.Space): Conversation {
        val threadPost = threadRepository.getThreadPost(
            threadId = spaceLocation.threadId,
            postId = spaceLocation.commentId
        )
        return Conversation(
            topic = TimelinePost(
                id = PostId(value = threadPost.id),
                senderName = threadPost.creator.name,
                postTime = threadPost.commentedAt,
                body = threadPost.body,
                location = spaceLocation
            ),
            comments = threadPost.comments.map { comment ->
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
        )
    }
}
