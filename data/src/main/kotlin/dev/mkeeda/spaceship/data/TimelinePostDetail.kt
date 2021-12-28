package dev.mkeeda.spaceship.data

import kotlinx.datetime.Instant

data class TimelinePostDetail(
    val senderName: String,
    val postTime: Instant,
    val body: String,
    val location: PostingLocation,
) : Comparable<TimelinePostDetail> {
    override fun compareTo(other: TimelinePostDetail): Int {
        return postTime.compareTo(other.postTime)
    }
}

sealed class PostingLocation {
    data class App(val appId: Long, val recordId: Long) : PostingLocation()
    data class Space(val threadId: Long, val commentId: Long) : PostingLocation()
    data class People(val threadId: Long, val commentId: Long) : PostingLocation()
    object Message : PostingLocation()
}
