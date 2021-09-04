package com.example.exampleapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.exampleapplication.data.model.person.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM persons")
    fun getAll() : Flow<List<Person>>

    @Query("SELECT * FROM persons WHERE id IN (:personIds)")
    fun loadAllByIds(personIds: IntArray) : Flow<List<Person>>

    @Insert
    fun insertAll(vararg persons: Person)

    @Delete
    fun delete(person: Person)
}