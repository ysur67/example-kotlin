package com.example.exampleapplication.data.database.dao

import androidx.room.*
import com.example.exampleapplication.data.model.Post
import io.reactivex.rxjava3.core.Flowable


@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll() : Flowable<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg post: Post)

    @Delete
    fun delete(post: Post)
}
