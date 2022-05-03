package dev.mkeeda.spaceship.infra.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.data.credential.NoLoginCredentialException
import dev.mkeeda.spaceship.infra.api.ntf.NtfList
import dev.mkeeda.spaceship.infra.api.ntf.NtfListService
import io.ktor.client.plugins.ResponseException

internal class TimelinePagingSource(
    private val networkRequestSize: Int,
    private val ntfListService: NtfListService
) : PagingSource<Int, TimelinePost>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TimelinePost> {
        val position = params.key ?: START_PAGING_INDEX
        return try {
            val response = ntfListService.getNtfList(
                param = NtfList.RequestParam(
                    checkIgnoreMention = true,
                    read = false,
                    size = networkRequestSize,
                    offset = networkRequestSize * position,
                )
            )
            val timelinePosts = response.toTimeline()
            val nextKey = if (timelinePosts.isEmpty()) {
                null
            } else {
                position + (params.loadSize / networkRequestSize)
            }
            LoadResult.Page(
                data = timelinePosts,
                prevKey = if (position == START_PAGING_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (cause: ResponseException) {
            LoadResult.Error(cause)
        } catch (cause: NoLoginCredentialException) {
            LoadResult.Error(cause)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TimelinePost>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val START_PAGING_INDEX = 0
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
