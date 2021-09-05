package com.example.exampleapplication.data.source

import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.person.Person
import retrofit2.Call


interface RemoteDataSource {
    fun requestPersons() : Call<MutableList<Person>>
    fun requestPosts() : Call<MutableList<Post>>
}
