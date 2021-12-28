package dev.mkeeda.spaceship.data

import kotlinx.datetime.Instant

data class TimelinePost(
    val id: PostId,
    val senderName: String,
    val postTime: Instant,
    val body: String,
)
