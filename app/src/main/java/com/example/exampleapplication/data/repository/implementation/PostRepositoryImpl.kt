package com.example.exampleapplication.data.repository.implementation

import com.example.exampleapplication.data.database.dao.PostDao
import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.PostWithPerson
import com.example.exampleapplication.data.source.RemoteDataSource
import com.example.exampleapplication.data.repository.PostRepository
import com.example.exampleapplication.data.source.LocalDataSource
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class PostRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
    ) : PostRepository {
    private val onPostResponseCallback = object : Callback<MutableList<Post>> {
        override fun onResponse(
            call: Call<MutableList<Post>>,
            response: Response<MutableList<Post>>
        ) {
            val requestResult = response.body() ?: return
            updateLocalDatabase(requestResult.toTypedArray())
        }

        override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
            throw t
        }

    }

    override fun updatePosts(): Flowable<List<Post>> {
        remoteDataSource.requestPosts().enqueue(onPostResponseCallback)
        return localDataSource.getAllPosts()
    }

    override fun getPosts(): Flowable<List<Post>> {
        return localDataSource.getAllPosts()
    }

    override fun searchPost(value: String): Flowable<List<PostWithPerson>> {
        val query = "%$value%"
        return localDataSource.getPostsByQuery(query)
    }

    override fun getPostsWithPerson(): Flowable<List<PostWithPerson>> {
        return localDataSource.getPostWithPerson()
    }

    private fun updateLocalDatabase(new: Array<Post>) {
        localDataSource.insertAllPosts(*new)
    }
}
