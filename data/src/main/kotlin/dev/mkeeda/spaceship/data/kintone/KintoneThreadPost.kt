package dev.mkeeda.spaceship.data.kintone

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class KintoneThreadPost(
    val id: Long,
    val threadId: Long,
    val commentCount: Int,
    val commentedAt: Instant,
    val comments: List<Comment>,
    val body: String,
    val creator: KintoneUser,
    val createdAt: Instant,
    val likeCount: Int,
    val liked: Boolean
)

@Serializable
data class Comment(
    val id: Long,
    val postId: Long,
    val body: String,
    val creator: KintoneUser,
    val createdAt: Instant,
    val likeCount: Int,
    val liked: Boolean,
)
