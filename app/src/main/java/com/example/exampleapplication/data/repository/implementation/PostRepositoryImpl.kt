package com.example.exampleapplication.data.repository.implementation

import com.example.exampleapplication.data.database.dao.PostDao
import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.remote.RemoteDataSource
import com.example.exampleapplication.data.repository.PostRepository
import io.reactivex.Flowable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val localDataSource: PostDao,
    private val remoteDataSource: RemoteDataSource
    ) : PostRepository {

    private val onPostRepsponseCallback = object : Callback<MutableList<Post>> {
        override fun onResponse(
            call: Call<MutableList<Post>>,
            response: Response<MutableList<Post>>
        ) {
            val requestResult = response.body() ?: return
            updateLocalDatabase(requestResult)
        }

        override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
            throw t
        }

    }

    override fun updatePosts(): Flowable<List<Post>> {
        remoteDataSource.requestPosts().enqueue(onPostRepsponseCallback)
        return localDataSource.getAll()
    }

    override fun getPosts(): Flowable<List<Post>> {
        return localDataSource.getAll()
    }

    private fun updateLocalDatabase(new: MutableList<Post>) {
        for (post in new) {
            GlobalScope.launch {
                localDataSource.insertAll(post)
            }
        }
    }
}