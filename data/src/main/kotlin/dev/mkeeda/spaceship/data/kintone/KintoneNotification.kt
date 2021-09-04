package dev.mkeeda.spaceship.data.kintone

import kotlinx.datetime.Instant

data class KintoneNotification(
    val id: Long,
    val moduleType: ModuleType,
    val moduleId: Long,
    val moduleSubId: Long,
    val commentId: Long,
    val commentReplyId: Long,
    val eventId: Int,
    val sender: Long?,
    val sentTime: Instant,
    val url: String,
    val mobileUrl: String,
    val read: Boolean,
    val mention: Boolean,
    val flagged: Boolean?,
    val groupKey: String,
    val groupKeyCount: Int,
    val content: Content
)

enum class ModuleType {
    APP, SPACE, PEOPLE, MESSAGE
}

data class Content(
    val title: Data,
    val subTitle: Data,
    val message: Data,
    val icon: String,
    val contents: List<String>
)

data class Data(
    val dataType: DataType,
    val text: String,
    val args: List<String>
)

enum class DataType {
    RESOURCE, PLAIN, HTML, IUSER, APP_NAME
}
