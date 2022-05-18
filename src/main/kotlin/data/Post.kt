package data

data class Post(
    val id: Int = 0,
    val authorId: Int,
    val authorName: String,
    val content: String,
    val created: Long,
    val likes: Int,
    val owner_id: Int? = null,
    val from_id: Int? = null,
    val friends_only: Int? = null,
    val comments: Int? = null,
    val attachments: List<Attachments>? = null
)