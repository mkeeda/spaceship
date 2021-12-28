package dev.mkeeda.spaceship.ui.timeline.presentation

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.TimelinePost
import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant

data class TimelineViewState(
    val postItems: List<TimelinePost>
) {
    companion object {
        val Initial = TimelineViewState(
            postItems = listOf()
        )

        internal val LongFake = TimelineViewState(
            postItems = fakeTimelinePostItems + fakeTimelinePostItems + fakeTimelinePostItems
        )
    }
}

internal fun fakeTimeline(
    id: PostId = PostId(1),
    senderName: String = "",
    postTime: Instant = "2021-08-16T14:21:00.000Z".toInstant(),
    body: String = "",
): TimelinePost = TimelinePost(
    id,
    senderName,
    postTime,
    body,
)

private val fakeTimelinePostItems = listOf(
    fakeTimeline(
        id = PostId(1),
        senderName = "森山みくり",
        body = "子どもを産むのに、順番待ちが必要って何？",
    ),
    fakeTimeline(
        id = PostId(2),
        senderName = "津崎平匡",
        body = "気づいたんです。料理は科学",
    ),
    fakeTimeline(
        id = PostId(3),
        senderName = "森山みくり",
        body = "どうして女の人だけが、自分の名字を捨てなきゃいけないのか",
    ),
    fakeTimeline(
        id = PostId(4),
        senderName = "森山みくり",
        body = "百合ちゃんの助けになれない。家のこともできない。仕事も行ってない。いいことひとつもない。うううう",
    ),
    fakeTimeline(
        id = PostId(5),
        senderName = "土屋百合",
        body = "体の不調がきついのは、みんな同じ。比べるものじゃないでしょ",
    ),
    fakeTimeline(
        id = PostId(6),
        senderName = "沼田頼綱",
        body = "誰が休んでも仕事が回る。帰ってこられる環境を普段から作っておくこと。それが職場におけるリスク管理",
    ),
)
