package dev.mkeeda.spaceship.ui.timeline.presentation

import dev.mkeeda.spaceship.data.PostingLocation
import dev.mkeeda.spaceship.data.TimelinePostDetail
import kotlin.random.Random
import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant

data class PostDetailsViewState(
    val comments: List<TimelinePostDetail> = emptyList(),
    val focusedCommentsIndex: Int = 0
) {
    companion object {
        val Initial = PostDetailsViewState()

        internal val Fake = PostDetailsViewState(
            comments = fakeComments,
            focusedCommentsIndex = 1
        )
    }
}

internal fun fakeTimelinePostDetail(
    senderName: String = "",
    postTime: Instant = "2021-08-16T14:21:00.000Z".toInstant(),
    body: String = "",
): TimelinePostDetail = TimelinePostDetail(
    senderName,
    postTime,
    body,
    PostingLocation.Space(
        threadId = Random.nextLong(),
        commentId = Random.nextLong(),
        commentReplyId = Random.nextLong()
    )
)

private val fakeComments = listOf(
    fakeTimelinePostDetail(
        senderName = "森山みくり",
        postTime = "2021-08-22T13:11:00.000Z".toInstant(),
        body = "親投稿だよ\nこんにちは"
    ),
    fakeTimelinePostDetail(
        senderName = "逃げ恥予告Bot",
        postTime = "2021-08-22T13:14:00.000Z".toInstant(),
        body = """
            家庭という仕事場の「共同経営者」である森山みくり(新垣結衣)と津崎平匡(星野 源)。共働きとなり、２人に最適な家事の分担も出来て、平和で幸せな日々を過ごしていた。

            女子社員の「出産の順番待ち」、みくりの会社ではそんな話題も出ていた。そんな中、みくりの妊娠が発覚。２人はついに正式に結婚をすることになるが、入籍をめぐってひと悶着が。さらに、つわりで苦しむみくりを前に混乱する平匡は、「泣きたいのはこっち」と弱音を吐く始末。大切なプロジェクトを前に育休を取ることを決める平匡だったが、そんな窮地を支えてくれたのは昔の同僚である沼田(古田新太)たちだった——。

            家事の役割分担など暮らしの再構築を目指すが、出産、育児と慣れない出来事の連続に翻弄されていくみくりと平匡。一方、百合ちゃん(石田 ゆり子)も人生の大きな転機を迎えていた･･･。

            そして、“ある出来事”がみくりと平匡の間を引き裂き、離ればなれの日々を過ごすことに･･･。

            ２人は再び“ハグの日”を取り戻すことは出来るのか——。
        """.trimIndent()
    ),
    fakeTimelinePostDetail(
        senderName = "森山みくり",
        postTime = "2021-08-22T14:11:00.000Z".toInstant(),
        body = "あとの投稿だよ"
    ),
    fakeTimelinePostDetail(
        senderName = "森山みくり",
        postTime = "2021-08-22T14:21:00.000Z".toInstant(),
        body = "あとの投稿だよ"
    ),
)
