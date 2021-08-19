package dev.mkeeda.spaceship.data

data class TimeLinePostDetails(
    val id: Int,
    val senderName: String,
    val postTime: String,
    val body: String,
)

fun fakeTimeListDetails(postId: Int): TimeLinePostDetails {
    return TimeLinePostDetails(
        id = postId,
        senderName = "sender detail",
        postTime = "time detail",
        body = "body detail"
    )
}
