package dev.mkeeda.spaceship.data

data class Conversation(
    val topic: TimelinePost,
    val comments: List<TimelinePost>? = null
)
