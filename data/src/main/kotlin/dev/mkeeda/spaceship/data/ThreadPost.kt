package dev.mkeeda.spaceship.data

interface ThreadPost {
    val id: Int
    val senderName: String
    val postTime: String
    val body: String
}

data class FocusedPost(
    override val id: Int,
    override val senderName: String,
    override val postTime: String,
    override val body: String,
) : ThreadPost

data class CommentPost(
    override val id: Int,
    override val senderName: String,
    override val postTime: String,
    override val body: String,
) : ThreadPost

fun fakeThreadPosts(postId: Int): List<ThreadPost> {
    return listOf(
        CommentPost(
            id = 0,
            senderName = "森山みくり",
            postTime = "2021/08/22 13:00",
            body = "親投稿だよ\nこんにちは"
        ),
        FocusedPost(
            id = postId,
            senderName = "逃げ恥予告Bot",
            postTime = "2021/08/22 13:31",
            body = """
            家庭という仕事場の「共同経営者」である森山みくり(新垣結衣)と津崎平匡(星野 源)。共働きとなり、２人に最適な家事の分担も出来て、平和で幸せな日々を過ごしていた。

            女子社員の「出産の順番待ち」、みくりの会社ではそんな話題も出ていた。そんな中、みくりの妊娠が発覚。２人はついに正式に結婚をすることになるが、入籍をめぐってひと悶着が。さらに、つわりで苦しむみくりを前に混乱する平匡は、「泣きたいのはこっち」と弱音を吐く始末。大切なプロジェクトを前に育休を取ることを決める平匡だったが、そんな窮地を支えてくれたのは昔の同僚である沼田(古田新太)たちだった——。

            家事の役割分担など暮らしの再構築を目指すが、出産、育児と慣れない出来事の連続に翻弄されていくみくりと平匡。一方、百合ちゃん(石田 ゆり子)も人生の大きな転機を迎えていた･･･。

            そして、“ある出来事”がみくりと平匡の間を引き裂き、離ればなれの日々を過ごすことに･･･。

            ２人は再び“ハグの日”を取り戻すことは出来るのか——。
        """.trimIndent()
        ),
        CommentPost(
            id = 0,
            senderName = "森山みくり",
            postTime = "2021/08/22 14:00",
            body = "あとの投稿だよ"
        ),
        CommentPost(
            id = 0,
            senderName = "森山みくり",
            postTime = "2021/08/22 14:00",
            body = "あとの投稿だよ"
        ),
    )
}
