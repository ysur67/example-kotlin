package com.example.exampleapplication.di.module

import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.remote.RemoteDataSource
import com.example.exampleapplication.data.repository.PersonRepository
import com.example.exampleapplication.data.repository.PersonRepositoryImpl
import com.example.exampleapplication.utils.Const
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
class RepositoryModule {
    @Provides
    @Singleton
    fun providePersonRepository(
        personDao: PersonDao,
        remoteDataSource: RemoteDataSource
    ) : PersonRepository {
        return PersonRepositoryImpl(personDao, remoteDataSource)
    }
}
