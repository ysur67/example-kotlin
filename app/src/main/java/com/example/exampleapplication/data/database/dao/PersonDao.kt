package com.example.exampleapplication.data.database.dao

import androidx.room.*
import com.example.exampleapplication.data.model.person.Person
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM persons")
    fun getAll() : Flowable<List<Person>>

    @Query("SELECT * FROM persons WHERE id IN (:personIds)")
    fun loadAllByIds(personIds: IntArray) : Flowable<List<Person>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg persons: Person)

    @Delete
    suspend fun delete(person: Person)
}