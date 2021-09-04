package com.example.exampleapplication.data.repository

import com.example.exampleapplication.data.model.person.Person
import io.reactivex.Flowable

interface Repository  {
    fun updatePosts() : Flowable<List<Person>>
    fun getPosts() : Flowable<List<Person>>
}
