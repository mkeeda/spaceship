package dev.mkeeda.spaceship.ui.timeline.state

data class TimelineViewState(
    val postItems: List<TimelinePost>
) {
    companion object {
        val longFake = TimelineViewState(
            postItems = fakeTimelinePostItems + fakeTimelinePostItems + fakeTimelinePostItems
        )
    }
}

data class TimelinePost(
    val id: Int,
    val senderName: String,
    val postTime: String,
    val body: String,
)

private val fakeTimelinePostItems = listOf(
    TimelinePost(
        id = 1,
        senderName = "森山みくり",
        postTime = "2021/08/16 14:26",
        body = "子どもを産むのに、順番待ちが必要って何？",
    ),
    TimelinePost(
        id = 2,
        senderName = "津崎平匡",
        postTime = "2021/08/16 14:26",
        body = "気づいたんです。料理は科学",
    ),
    TimelinePost(
        id = 3,
        senderName = "森山みくり",
        postTime = "2021/08/16 14:26",
        body = "どうして女の人だけが、自分の名字を捨てなきゃいけないのか",
    ),
    TimelinePost(
        id = 4,
        senderName = "森山みくり",
        postTime = "2021/08/16 14:26",
        body = "百合ちゃんの助けになれない。家のこともできない。仕事も行ってない。いいことひとつもない。うううう",
    ),
    TimelinePost(
        id = 5,
        senderName = "土屋百合",
        postTime = "2021/08/16 14:26",
        body = "体の不調がきついのは、みんな同じ。比べるものじゃないでしょ",
    ),
    TimelinePost(
        id = 6,
        senderName = "沼田頼綱",
        postTime = "2021/08/16 14:26",
        body = "誰が休んでも仕事が回る。帰ってこられる環境を普段から作っておくこと。それが職場におけるリスク管理",
    ),
)
