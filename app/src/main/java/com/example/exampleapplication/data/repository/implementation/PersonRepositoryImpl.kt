package com.example.exampleapplication.data.repository.implementation

import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.model.person.Person
import com.example.exampleapplication.data.source.RemoteDataSource
import com.example.exampleapplication.data.repository.PersonRepository
import com.example.exampleapplication.data.source.LocalDataSource
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
    ) : PersonRepository {
    private val onPersonResponseCallback = object : retrofit2.Callback<MutableList<Person>> {
        override fun onResponse(
            call: Call<MutableList<Person>>,
            response: Response<MutableList<Person>>
        ) {
            val requestResult = response.body() ?: return
            updateLocalDatabase(requestResult.toTypedArray())
        }
        override fun onFailure(call: Call<MutableList<Person>>, t: Throwable) {
            throw t
        }
    }

    override fun updatePersons(): Flowable<List<Person>> {
        remoteDataSource.requestPersons().enqueue(onPersonResponseCallback)
        return localDataSource.getAllPersons()
    }

    override fun getPersons(): Flowable<List<Person>> {
        return localDataSource.getAllPersons()
    }

    private fun updateLocalDatabase(new: Array<Person>) {
        localDataSource.insertAllPersons(*new)
    }
}
