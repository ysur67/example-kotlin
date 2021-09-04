package com.example.exampleapplication.data.repository

import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.model.person.Person
import io.reactivex.Flowable
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    val localDataSource: PersonDao,
    ) : PersonRepository {
    override fun updatePersons(): Flowable<List<Person>> {
        TODO("Not yet implemented")
    }

    override fun getPersons(): Flowable<List<Person>> {
        return localDataSource.getAll()
    }
}
