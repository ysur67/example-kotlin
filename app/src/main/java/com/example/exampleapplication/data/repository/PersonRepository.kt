package com.example.exampleapplication.data.repository

import com.example.exampleapplication.data.model.person.Person
import io.reactivex.Flowable

interface PersonRepository {
    fun updatePersons() : Flowable<List<Person>>
    fun getPersons() : Flowable<List<Person>>
}
