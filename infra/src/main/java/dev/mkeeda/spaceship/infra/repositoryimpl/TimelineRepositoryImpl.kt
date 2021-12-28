package dev.mkeeda.spaceship.infra.repositoryimpl

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.PostingLocation
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.data.TimelinePostDetail
import dev.mkeeda.spaceship.data.kintone.KintoneNotification
import dev.mkeeda.spaceship.data.kintone.ModuleType
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import dev.mkeeda.spaceship.infra.api.KintoneApiService
import dev.mkeeda.spaceship.infra.api.ntf.NtfGet
import dev.mkeeda.spaceship.infra.api.ntf.NtfList
import javax.inject.Inject

class TimelineRepositoryImpl @Inject constructor(
    private val kintoneApiService: KintoneApiService
) : TimelineRepository {
    override suspend fun getTimelinePostList(): List<TimelinePost> {
        val response = kintoneApiService.post<NtfList.Response>(
            endpoint = NtfList,
            param = NtfList.RequestParam(
                mentioned = false,
                read = true,
            )
        )
        return response.toTimeline()
    }

    override suspend fun getTimelinePost(postId: PostId): TimelinePostDetail {
        val response = kintoneApiService.post<NtfGet.Response>(
            endpoint = NtfGet,
            param = NtfGet.RequestParam(
                id = postId.value
            )
        )
        return response.toTimelinePostDetail()
    }
}

private fun NtfList.Response.toTimeline(): List<TimelinePost> {
    return ntf.map { kintoneNotification ->
        TimelinePost(
            id = PostId(value = kintoneNotification.id),
            senderName = senders[kintoneNotification.sender]?.name ?: "",
            postTime = kintoneNotification.sentTime,
            body = kintoneNotification.content.message.text,
        )
    }
}

private fun NtfGet.Response.toTimelinePostDetail(): TimelinePostDetail {
    return TimelinePostDetail(
        senderName = sender?.name ?: "",
        postTime = item.sentTime,
        body = item.content.message.text,
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
            commentId = commentId
        )
        ModuleType.PEOPLE -> PostingLocation.People(
            threadId = moduleId,
            commentId = commentId
        )
        ModuleType.MESSAGE -> PostingLocation.Message
    }
