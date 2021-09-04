package com.example.exampleapplication.di.module

import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.repository.PersonRepository
import com.example.exampleapplication.data.repository.PersonRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun providePersonRepository(personDao: PersonDao) : PersonRepository {
        return PersonRepositoryImpl(personDao)
    }
}
