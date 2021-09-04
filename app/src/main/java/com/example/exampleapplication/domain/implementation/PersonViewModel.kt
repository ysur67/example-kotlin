package com.example.exampleapplication.domain.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.data.model.person.Person
import com.example.exampleapplication.data.repository.PersonRepository
import com.example.exampleapplication.data.repository.PostRepository
import com.example.exampleapplication.domain.BaseViewModel
import javax.inject.Inject

class PersonViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val postRepository: PostRepository
) : BaseViewModel() {

    private val _persons = MutableLiveData<ArrayList<Person>>(null)
    val persons: LiveData<ArrayList<Person>>
        get() = _persons

    fun loadPersons() {
        personRepository.getPersons()
            .map { updatePersonLiveData(it) }
    }

    fun updatePersons() {
        personRepository.updatePersons().doOnNext { updatePersonLiveData(it) }
    }

    fun updatePosts() {
        postRepository.updatePosts().doOnNext { updatePostLiveData(it) }
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

    private fun updatePostLiveData(it: List<Post>?) {
//        var currentValue = _post.value
    }
}