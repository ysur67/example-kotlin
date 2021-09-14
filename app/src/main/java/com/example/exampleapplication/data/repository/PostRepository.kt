package com.example.exampleapplication.data.repository

import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.PostWithPerson
import io.reactivex.rxjava3.core.Flowable

interface PostRepository {
    fun updatePosts() : Flowable<List<Post>>
    fun getPosts() : Flowable<List<Post>>
    fun searchPost(value: String) : Flowable<List<Post>>

    fun getPostsWithPerson() : Flowable<List<PostWithPerson>>
}
