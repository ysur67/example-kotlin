package com.example.exampleapplication.data.repository

import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.model.person.Person
import com.example.exampleapplication.data.remote.RemoteDataSource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class PersonRepositoryImpl @Inject constructor(
    private val localDataSource: PersonDao,
    private val remoteDataSource: RemoteDataSource
    ) : PersonRepository {
    override fun updatePersons(): Flowable<List<Person>> {
        val callback = object : retrofit2.Callback<MutableList<Person>> {
            override fun onResponse(
                call: Call<MutableList<Person>>,
                response: Response<MutableList<Person>>
            ) {
                val requestResult = response.body() ?: return
                updateLocalDatabase(requestResult)
            }
            override fun onFailure(call: Call<MutableList<Person>>, t: Throwable) {
                throw t
            }
        }
        remoteDataSource.requestPersons().enqueue(callback)
        return localDataSource.getAll()
    }

    override fun getPersons(): Flowable<List<Person>> {
        return localDataSource.getAll()
    }

    private fun updateLocalDatabase(new: MutableList<Person>) {
        for (person in new) {
            GlobalScope.launch {
                localDataSource.insertAll(person)
            }
        }
    }
}
