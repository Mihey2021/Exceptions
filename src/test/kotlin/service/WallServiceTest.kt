package service

import data.*
import exceptions.PostNotFoundException
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {
    @Before
    fun clear() {
        WallService.clearWall()
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        WallService.createComment(Comment(1, 1, 0, 20220518, "Комментарий к несуществующему посту"))
    }

    @Test
    fun addComment() {
        //1. Arrange — подготовка данных (задание переменных).
        val authorId = 1
        val authorName = "test"
        val content = "Test content"
        val created: Long = 20220423
        val likes = 0
        val post =
            Post(
                authorId = authorId,
                authorName = authorName,
                content = content,
                created = created,
                likes = likes,
                owner_id = null,
                from_id = null,
                friends_only = null
            )

        //2.Act — выполнение целевого действия (вызов функции).
        WallService.add(post)
        val commentAdded = WallService.createComment(Comment(1, 1, 0, 20220518, "Комментарий к существующему посту"))

        //3.Assert — сравнение ожидаемого результата с фактическим.
        assertEquals(true, commentAdded)
    }

    @Test
    fun addPostWithAllAttachmentTypes() {
        //1. Arrange — подготовка данных (задание переменных).
        val authorId = 1
        val authorName = "test"
        val content = "Test content"
        val created: Long = 20220423
        val likes = 0
        val post =
            Post(
                authorId = authorId,
                authorName = authorName,
                content = content,
                created = created,
                likes = likes,
                owner_id = null,
                from_id = null,
                friends_only = null,
                comments = null,
                attachments = listOf(
                    AttachmentNote(
                        Note(1, 3, 20220515, "Первая заметка", "Это первая заметка", 0)
                    ),
                    AttachmentAudio(
                        Audio(1, 3, 20220515, "Unknown", 120, 1)
                    ),
                    AttachmentVideo(
                        Video(1,0,20220518, "Test video","Это тестовое видео", 3600, 640, 480)
                    ),
                    AttachmentPhoto(
                        Photo(1,0,20220518,0,0,"Testing photo", 1024, 768)
                    ),
                    AttachmentDoc(
                        Doc(1,0,20220518,"Testing document", 2048, "pdf", 0 )
                    )
                )
            )

        //2.Act — выполнение целевого действия (вызов функции).
        val createdPost = WallService.add(post)

        //3.Assert — сравнение ожидаемого результата с фактическим.
        assertNotEquals(0, createdPost.id)
    }

    @Test
    fun add() {
        //1. Arrange — подготовка данных (задание переменных).
        val authorId = 1
        val authorName = "test"
        val content = "Test content"
        val created: Long = 20220423
        val likes = 0
        val post =
            Post(
                authorId = authorId,
                authorName = authorName,
                content = content,
                created = created,
                likes = likes,
                owner_id = null,
                from_id = null,
                friends_only = null
            )

        //2.Act — выполнение целевого действия (вызов функции).
        val createdPost = WallService.add(post)

        //3.Assert — сравнение ожидаемого результата с фактическим.
        assertNotEquals(0, createdPost.id)
    }

    @Test
    fun update_existingPost() {
        //1. Arrange — подготовка данных (задание переменных).
        val authorId = 1
        val authorName = "test"
        val content = "Test content"
        val created: Long = 20220423
        val likes = 0
        val post =
            Post(
                authorId = authorId,
                authorName = authorName,
                content = content,
                created = created,
                likes = likes,
                owner_id = null,
                from_id = null,
                friends_only = null
            )

        val updatingId = 1
        val updatingAuthorId = 1
        val updatingAuthorName = "test"
        val updatingContent = "Content after updating"
        val updatingCreated: Long = 19970101
        val updatingLikes = 0
        val updatingPost =
            Post(
                id = updatingId,
                authorId = updatingAuthorId,
                authorName = updatingAuthorName,
                content = updatingContent,
                created = updatingCreated,
                likes = updatingLikes
            )

        //2.Act — выполнение целевого действия (вызов функции).
        WallService.add(post)
        val result = WallService.update(updatingPost)

        //3.Assert — сравнение ожидаемого результата с фактическим.
        assertEquals(true, result)
    }

    @Test
    fun update_notExistingPost() {
        //1. Arrange — подготовка данных (задание переменных).
        val authorId = 1
        val authorName = "test"
        val content = "Test content"
        val created: Long = 20220423
        val likes = 0
        val post =
            Post(
                authorId = authorId,
                authorName = authorName,
                content = content,
                created = created,
                likes = likes,
                owner_id = null,
                from_id = null,
                friends_only = null
            )

        val updatingId = 17 //несуществующий пост
        val updatingAuthorId = 1
        val updatingAuthorName = "test"
        val updatingContent = "Content after updating"
        val updatingCreated: Long = 19970101
        val updatingLikes = 0
        val updatingPost =
            Post(
                id = updatingId,
                authorId = updatingAuthorId,
                authorName = updatingAuthorName,
                content = updatingContent,
                created = updatingCreated,
                likes = updatingLikes
            )

        //2.Act — выполнение целевого действия (вызов функции).
        WallService.add(post)
        val result = WallService.update(updatingPost)

        //3.Assert — сравнение ожидаемого результата с фактическим.
        assertEquals(false, result)
    }

    @Test
    fun getLastPostId_emptyWall() {
        //1. Arrange — подготовка данных (задание переменных).
        //ничего не задаем

        //2.Act — выполнение целевого действия (вызов функции).
        val result = WallService.getLastPostId()

        //3.Assert — сравнение ожидаемого результата с фактическим.
        assertEquals(0, result)
    }

    @Test
    fun getLastPostId_notEmptyWall() {
        //1. Arrange — подготовка данных (задание переменных).
        val authorId = 1
        val authorName = "test"
        val content = "Test content"
        val created: Long = 20220423
        val likes = 0
        val post =
            Post(
                authorId = authorId,
                authorName = authorName,
                content = content,
                created = created,
                likes = likes,
                owner_id = null,
                from_id = null,
                friends_only = null
            )

        //2.Act — выполнение целевого действия (вызов функции).
        WallService.add(post)
        val result = WallService.getLastPostId()

        //3.Assert — сравнение ожидаемого результата с фактическим.
        assertNotEquals(0, result)
    }
}