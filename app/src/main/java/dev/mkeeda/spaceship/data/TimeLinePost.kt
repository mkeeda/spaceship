package dev.mkeeda.spaceship.data

data class TimeLinePost(
    val senderName: String,
    val postTime: String,
    val body: String,
)

val fakeTimeLinePostItems = listOf(
    TimeLinePost(
        senderName = "森山みくり",
        postTime = "2021/08/16 14:26",
        body = "子どもを産むのに、順番待ちが必要って何？",
    ),
    TimeLinePost(
        senderName = "津崎平匡",
        postTime = "2021/08/16 14:26",
        body = "気づいたんです。料理は科学",
    ),
    TimeLinePost(
        senderName = "森山みくり",
        postTime = "2021/08/16 14:26",
        body = "どうして女の人だけが、自分の名字を捨てなきゃいけないのか",
    ),
)
