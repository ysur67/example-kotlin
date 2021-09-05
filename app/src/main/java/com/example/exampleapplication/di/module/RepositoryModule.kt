package com.example.exampleapplication.di.module

import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.database.dao.PostDao
import com.example.exampleapplication.data.source.RemoteDataSource
import com.example.exampleapplication.data.repository.PersonRepository
import com.example.exampleapplication.data.repository.PostRepository
import com.example.exampleapplication.data.repository.implementation.PersonRepositoryImpl
import com.example.exampleapplication.data.repository.implementation.PostRepositoryImpl
import com.example.exampleapplication.data.source.LocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
class RepositoryModule {
    @Provides
    @Singleton
    fun providePersonRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) : PersonRepository {
        return PersonRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) : PostRepository {
        return PostRepositoryImpl(localDataSource, remoteDataSource)
    }
}
