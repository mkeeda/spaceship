package dev.mkeeda.spaceship.infra.repositoryimpl

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import dev.mkeeda.spaceship.infra.api.KintoneApiService
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
