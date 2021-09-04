package com.example.exampleapplication.data.repository

import com.example.exampleapplication.data.model.Post
import io.reactivex.Flowable

interface PostRepository {
    fun updatePosts() : Flowable<List<Post>>
    fun getPosts() : Flowable<List<Post>>
}
