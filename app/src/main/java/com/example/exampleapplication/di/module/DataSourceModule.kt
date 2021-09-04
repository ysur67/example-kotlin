package com.example.exampleapplication.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exampleapplication.data.database.AppDatabase
import com.example.exampleapplication.data.database.dao.PersonDao
import dagger.Module
import dagger.Provides
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
        ).build()
    }

    @Provides
    @Singleton
    fun providePersonDao(database: AppDatabase) : PersonDao {
        return database.personDao()
    }
}
