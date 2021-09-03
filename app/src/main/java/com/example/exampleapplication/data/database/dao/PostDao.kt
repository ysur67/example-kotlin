package com.example.exampleapplication.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.exampleapplication.data.model.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll() : List<Post>

    @Insert
    fun insertAll(vararg post: Post)

    @Delete
    fun delete(post: Post)
}
