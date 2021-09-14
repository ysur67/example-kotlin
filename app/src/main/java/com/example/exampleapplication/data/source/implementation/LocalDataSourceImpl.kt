package com.example.exampleapplication.data.source.implementation

import com.example.exampleapplication.data.database.dao.PersonDao
import com.example.exampleapplication.data.database.dao.PostDao
import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.PostWithPerson
import com.example.exampleapplication.data.model.person.Person
import com.example.exampleapplication.data.source.LocalDataSource
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val personDao: PersonDao,
    private val postDao: PostDao
    ) : LocalDataSource {

    private val localScope = CoroutineScope(Job() + Dispatchers.Default)

    override fun getAllPersons(): Flowable<List<Person>> {
        return personDao.getAll()
    }

    override fun getPersonsByIds(ids: IntArray): Flowable<List<Person>> {
        return personDao.loadAllByIds(ids)
    }

    override fun getAllPosts(): Flowable<List<Post>> {
        return postDao.getAll()
    }

    override fun insertAllPersons(vararg persons: Person) {
        localScope.launch {
            personDao.insertAll(*persons)
        }
    }

    override fun insertAllPosts(vararg posts: Post) {
        localScope.launch {
            postDao.insertAll(*posts)
        }
    }

    override fun deletePerson(instance: Person) {
        localScope.launch {
            personDao.delete(instance)
        }
    }

    override fun deletePost(instance: Post) {
        localScope.launch {
            postDao.delete(instance)
        }
    }

    override fun getPostsByQuery(query: String): Flowable<List<Post>> {
        return postDao.getPostByQuery(query)
    }

    override fun getPostWithPerson(): Flowable<List<PostWithPerson>> {
        return postDao.getPostsWithPerson()
    }
}
