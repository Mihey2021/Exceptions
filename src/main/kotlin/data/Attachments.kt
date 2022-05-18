package data

sealed class Attachments(
    val type: String
)

data class AttachmentPhoto(
    val photo: Photo
) : Attachments("photo")

data class AttachmentDoc(
    val doc: Doc
) : Attachments("doc")

data class AttachmentVideo(
    val video: Video
) : Attachments("video")

data class AttachmentAudio(
    val audio: Audio
) : Attachments("audio")

data class AttachmentNote(
    val note: Note
) : Attachments("note")

data class Photo(
    val id: Int,
    val owner_id: Int,
    val date: Int,
    val album_id: Int,
    val user_id: Int,
    val text: String,
    val width: Int,
    val height: Int
)

data class Doc(
    val id: Int,
    val owner_id: Int,
    val date: Int,
    val title: String,
    val size: Int,
    val ext: String,
    val type: Int
)

data class Video(
    val id: Int,
    val owner_id: Int,
    val date: Int,
    val title: String,
    val description: String,
    val duration: Int,
    val width: Int,
    val height: Int
)

data class Audio(
    val id: Int,
    val owner_id: Int,
    val date: Int,
    val artist: String,
    val duration: Int,
    val album_id: Int
) {
    override fun toString(): String {
        return "Исполнитель: $artist, Длительность: ${duration / 60} мин."
    }
}

data class Note(
    val id: Int,
    val owner_id: Int,
    val date: Int,
    val title: String,
    val text: String,
    val comments: Int
) {
    override fun toString(): String {
        return "Заголовок: $title, Текст: $text"
    }
}


