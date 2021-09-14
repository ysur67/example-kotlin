package com.example.exampleapplication.domain.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.PostWithPerson
import com.example.exampleapplication.data.model.person.Person
import com.example.exampleapplication.data.repository.PersonRepository
import com.example.exampleapplication.data.repository.PostRepository
import com.example.exampleapplication.domain.BaseViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PersonViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val postRepository: PostRepository
) : BaseViewModel() {

    private val _persons = MutableLiveData<ArrayList<Person>>(null)
    val persons: LiveData<ArrayList<Person>>
        get() = _persons

    private val _posts = MutableLiveData<ArrayList<Post>>(null)
    val posts: LiveData<ArrayList<Post>>
        get() = _posts

    private val _postsWithPerson = MutableLiveData<ArrayList<PostWithPerson>>(null)
    val postsWithPerson: LiveData<ArrayList<PostWithPerson>>
        get() = _postsWithPerson

    fun loadPersons() {
        loading = true
        personRepository.getPersons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorComplete {
                throw it
            }
            .subscribe{
                updatePersonLiveData(it)
            }
    }

    fun loadPosts() {
        postRepository.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorComplete{
                throw it
            }
            .subscribe{
                updatePostLiveData(it)
            }
    }

    fun updatePersons() {
        personRepository.updatePersons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorComplete{
                throw it
            }
            .subscribe{
                updatePersonLiveData(it)
            }
    }

    fun updatePosts() {
        _posts.postValue(ArrayList())
        postRepository.updatePosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorComplete{
                throw it
            }
            .subscribe{
                updatePostLiveData(it)
            }
    }

    fun searchPost(value: String) {
        loading = true
        postRepository.searchPost(value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorComplete{
                throw it
            }
            .subscribe{
                clearDataSet()
                _postsWithPerson.postValue(it as ArrayList<PostWithPerson>)
                loading = false
            }
    }

    fun loadPostsWithPerson() {
        loading = true
        postRepository.getPostsWithPerson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorComplete{
                throw it
            }
            .subscribe{
                _postsWithPerson.postValue(it as ArrayList<PostWithPerson>)
                loading = false
            }
    }

    private fun clearDataSet() {
        _posts.postValue(ArrayList())
    }

    private fun updatePersonLiveData(new: List<Person>) {
        _persons.postValue(new as ArrayList<Person>)
        loading = false
    }

    private fun updatePostLiveData(new: List<Post>) {
        _posts.postValue(new as ArrayList<Post>)
        loading = false
    }
}
