package com.example.exampleapplication.data.source

import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.person.Person
import io.reactivex.rxjava3.core.Flowable

interface LocalDataSource {
    fun getAllPersons() : Flowable<List<Person>>
    fun getPersonsByIds(ids: IntArray) : Flowable<List<Person>>
    fun getAllPosts() : Flowable<List<Post>>

    fun insertAllPersons(vararg persons: Person)
    fun insertAllPosts(vararg posts: Post)

    fun deletePerson(instance: Person)
    fun deletePost(instance: Post)

    fun getPostsByQuery(query: String) : Flowable<List<Post>>
}
