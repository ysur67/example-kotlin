package com.example.exampleapplication.data.repository

import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.model.person.Person
import io.reactivex.Flowable
import javax.inject.Inject

class PersonRepository @Inject constructor(
    val localDataSource: PersonDao,
    ) : Repository {
    override fun updatePosts(): Flowable<List<Person>> {
        TODO("Not yet implemented")
    }

    override fun getPosts(): Flowable<List<Person>> {
        return localDataSource.getAll()
    }
}
