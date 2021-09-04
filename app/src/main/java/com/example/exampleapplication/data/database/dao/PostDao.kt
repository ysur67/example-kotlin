package com.example.exampleapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.exampleapplication.data.model.Post
import io.reactivex.Flowable


@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll() : Flowable<List<Post>>

    @Insert
    fun insertAll(vararg post: Post)

    @Delete
    fun delete(post: Post)
}
