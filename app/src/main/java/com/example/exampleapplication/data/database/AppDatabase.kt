package com.example.exampleapplication.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.database.dao.PostDao
import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.person.Address
import com.example.exampleapplication.data.model.person.Company
import com.example.exampleapplication.data.model.person.Person

@Database(
    entities = [Person::class, Post::class],
    version = 4
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao() : PersonDao
    abstract fun postDao() : PostDao
}