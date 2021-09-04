package com.example.exampleapplication.di.module

import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.database.dao.PostDao
import com.example.exampleapplication.data.remote.RemoteDataSource
import com.example.exampleapplication.data.repository.PersonRepository
import com.example.exampleapplication.data.repository.PostRepository
import com.example.exampleapplication.data.repository.implementation.PersonRepositoryImpl
import com.example.exampleapplication.data.repository.implementation.PostRepositoryImpl
import dagger.Module
import dagger.Provides
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

    @Provides
    @Singleton
    fun providePostRepository(
        postDao: PostDao,
        remoteDataSource: RemoteDataSource
    ) : PostRepository {
        return PostRepositoryImpl(postDao, remoteDataSource)
    }
}
