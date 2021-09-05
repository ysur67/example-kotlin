package com.example.exampleapplication.data.source.implementation

import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.person.Person
import com.example.exampleapplication.data.source.RemoteDataSource
import com.example.exampleapplication.data.remote.RetrofitServices
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    val retrofit: RetrofitServices
    ) : RemoteDataSource {
    override fun requestPersons(): Call<MutableList<Person>> {
        return retrofit.getPersonList()
    }

    override fun requestPosts(): Call<MutableList<Post>> {
        return retrofit.getAllPosts()
    }
}
