package com.example.exampleapplication.domain.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exampleapplication.data.model.person.Person
import com.example.exampleapplication.data.repository.PersonRepository
import com.example.exampleapplication.domain.BaseViewModel
import javax.inject.Inject

class PersonViewModel @Inject constructor(
    val repository: PersonRepository
) : BaseViewModel() {

    private val _persons = MutableLiveData<ArrayList<Person>>(null)
    val persons: LiveData<ArrayList<Person>>
        get() = _persons

    fun loadPersons() {
        repository.getPersons()
            .map { updatePersons(it) }
    }

    fun updatePersons() {
        repository.updatePersons().doOnNext {
            updatePersons(it)
        }
    }

    private fun updatePersons(new: List<Person>) {
        var currentValue = _persons.value
        if (currentValue == null) {
            _persons.postValue(new as ArrayList<Person>)
            return
        }
        for (person in new) {
            currentValue.add(person)
        }
        _persons.postValue(currentValue)
    }
}