package com.example.exampleapplication.data.remote

import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.person.Person
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("users")
    fun getPersonList() : Call<MutableList<Person>>

    @GET("posts")
    fun getAllPosts() : Call<MutableList<Post>>
}