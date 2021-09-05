package com.example.exampleapplication.domain.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exampleapplication.data.model.Post
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

    fun loadPersons() {
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

    private fun updatePersonLiveData(new: List<Person>) {
        var currentValue = _persons.value
        if (currentValue == null) {
            _persons.postValue(new as ArrayList<Person>)
            return
        }
        for (person in new) {
            currentValue.add(person)
        }
        _persons.postValue(currentValue!!)
    }

    private fun updatePostLiveData(new: List<Post>) {
        var currentValue = _posts.value
        if (currentValue == null) {
            _posts.postValue(new as ArrayList<Post>)
            return
        }
        for (post in new) {
            currentValue.add(post)
        }
        _posts.postValue(currentValue!!)
    }
}