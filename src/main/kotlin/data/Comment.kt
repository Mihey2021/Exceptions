package data

data class Comment(
    val id: Int,
    val postId: Int,
    val from_id: Int,
    val date: Int,
    val text: String,
    val attachments: List<Attachments>? = null
)