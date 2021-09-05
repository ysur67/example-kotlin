package com.example.exampleapplication.di.module

import android.app.Application
import androidx.room.Room
import com.example.exampleapplication.data.database.AppDatabase
import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.database.dao.PostDao
import com.example.exampleapplication.data.remote.RemoteDataSource
import com.example.exampleapplication.data.remote.RetrofitServices
import com.example.exampleapplication.data.remote.implementation.RemoteDataSourceImpl
import com.example.exampleapplication.utils.Const
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class DataSourceModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "post-database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providePersonDao(database: AppDatabase) : PersonDao {
        return database.personDao()
    }

    @Provides
    @Singleton
    fun providePostDao(database: AppDatabase) : PostDao {
        return database.postDao()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(client: Retrofit) : RetrofitServices {
        return client.create(RetrofitServices::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(retrofitServices: RetrofitServices) : RemoteDataSource {
        return RemoteDataSourceImpl(retrofitServices)
    }
}
