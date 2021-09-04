package com.example.exampleapplication.data.repository

import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.model.person.Person
import com.example.exampleapplication.data.remote.RemoteDataSource
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class PersonRepositoryImpl @Inject constructor(
    val localDataSource: PersonDao,
    val remoteDataSource: RemoteDataSource
    ) : PersonRepository {
    override fun updatePersons(): Flowable<List<Person>> {
//        remoteDataSource.requestPersons().execute().body()?.forEach {
//            localDataSource.insertAll(it)
//        }
        val callback = object : retrofit2.Callback<MutableList<Person>> {
            override fun onResponse(
                call: Call<MutableList<Person>>,
                response: Response<MutableList<Person>>
            ) {
                throw NotImplementedError("Данные получены")
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
}
