package ru.netology.test

data class Post(
    val id: Long,
    val authorId: Long,
    val author: String,
    val authorAvatar: String?,
    val authorJob: String?,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val likes: Int,
)