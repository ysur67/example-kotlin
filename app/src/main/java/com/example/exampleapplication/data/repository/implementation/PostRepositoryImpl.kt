package com.example.exampleapplication.data.repository.implementation

import android.provider.Settings
import com.example.exampleapplication.data.database.dao.PostDao
import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.remote.RemoteDataSource
import com.example.exampleapplication.data.repository.PostRepository
import io.reactivex.Flowable
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class PostRepositoryImpl @Inject constructor(
    private val localDataSource: PostDao,
    private val remoteDataSource: RemoteDataSource
    ) : PostRepository {

    private val localScope = CoroutineScope(Job() + Dispatchers.Default)

    private val onPostResponseCallback = object : Callback<MutableList<Post>> {
        override fun onResponse(
            call: Call<MutableList<Post>>,
            response: Response<MutableList<Post>>
        ) {
            val requestResult = response.body() ?: return
            localScope.launch {
                updateLocalDatabase(requestResult.toTypedArray())
            }
        }

        override fun onFailure(call: Call<MutableList<Post>>, t: Throwable) {
            throw t
        }

    }

    override fun updatePosts(): Flowable<List<Post>> {
        remoteDataSource.requestPosts().enqueue(onPostResponseCallback)
        return localDataSource.getAll()
    }

    override fun getPosts(): Flowable<List<Post>> {
        return localDataSource.getAll()
    }

    private suspend fun updateLocalDatabase(new: Array<Post>) = coroutineScope {
        launch {
            localDataSource.insertAll(*new)
        }
    }
}
