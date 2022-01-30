package dev.mkeeda.spaceship.infra.repositoryimpl

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.PostingLocation
import dev.mkeeda.spaceship.data.TimelinePostDetail
import dev.mkeeda.spaceship.data.kintone.KintoneNotification
import dev.mkeeda.spaceship.data.kintone.ModuleType
import dev.mkeeda.spaceship.domain.repository.PostDetailsRepository
import dev.mkeeda.spaceship.infra.api.ntf.NtfGet
import dev.mkeeda.spaceship.infra.api.ntf.NtfGetService
import javax.inject.Inject

class PostDetailsRepositoryImpl @Inject constructor(
    private val ntfGetService: NtfGetService
) : PostDetailsRepository {
    override suspend fun getPostDetail(postId: PostId): TimelinePostDetail {
        val response = ntfGetService.getNtfGet(
            param = NtfGet.RequestParam(
                id = postId.value
            )
        )
        return response.toTimelinePostDetail()
    }
}

private fun NtfGet.Response.toTimelinePostDetail(): TimelinePostDetail {
    return TimelinePostDetail(
        senderName = sender?.name ?: "",
        postTime = item.sentTime,
        htmlBody = item.content.message.text,
        location = item.postingLocation
    )
}

private val KintoneNotification.postingLocation: PostingLocation
    get() = when (moduleType) {
        ModuleType.APP -> PostingLocation.App(
            appId = moduleId,
            recordId = moduleSubId
        )
        ModuleType.SPACE -> PostingLocation.Space(
            threadId = moduleSubId,
            commentId = commentId,
            commentReplyId = commentReplyId
        )
        ModuleType.PEOPLE -> PostingLocation.People(
            threadId = moduleId,
            commentId = commentId
        )
        ModuleType.MESSAGE -> PostingLocation.Message
    }
