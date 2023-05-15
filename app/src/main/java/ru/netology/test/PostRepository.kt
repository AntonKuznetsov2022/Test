package ru.netology.test

import androidx.lifecycle.LiveData

interface PostRepository {
    val data: LiveData<List<Post>>
    suspend fun get()
}