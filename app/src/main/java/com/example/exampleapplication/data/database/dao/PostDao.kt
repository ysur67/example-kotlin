package com.example.exampleapplication.data.database.dao

import androidx.room.*
import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.PostWithPerson
import io.reactivex.rxjava3.core.Flowable


@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll() : Flowable<List<Post>>

    @Query("SELECT * FROM post")
    fun getPostsWithPerson() : Flowable<List<PostWithPerson>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg post: Post)

    @Delete
    suspend fun delete(post: Post)

    @Query("SELECT * FROM post WHERE title LIKE (:query) OR body LIKE (:query)")
    fun getPostByQuery(query: String) : Flowable<List<Post>>
}
