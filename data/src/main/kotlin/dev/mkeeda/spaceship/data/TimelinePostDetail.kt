package dev.mkeeda.spaceship.data

import kotlinx.datetime.Instant

data class TimelinePostDetail(
    val senderName: String,
    val postTime: Instant,
    val htmlBody: String,
    val location: PostingLocation,
) : Comparable<TimelinePostDetail> {
    override fun compareTo(other: TimelinePostDetail): Int {
        return postTime.compareTo(other.postTime)
    }

    override fun equals(other: Any?): Boolean {
        return other is TimelinePostDetail && location == other.location
    }

    override fun hashCode(): Int {
        return location.hashCode()
    }
}

sealed class PostingLocation {
    data class App(val appId: Long, val recordId: Long) : PostingLocation()
    data class Space(val threadId: Long, val commentId: Long, val commentReplyId: Long?) : PostingLocation()
    data class People(val threadId: Long, val commentId: Long) : PostingLocation()
    object Message : PostingLocation()
}
