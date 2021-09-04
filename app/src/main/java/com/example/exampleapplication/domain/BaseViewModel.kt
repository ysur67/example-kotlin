package com.example.exampleapplication.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    protected var loading: Boolean? = null
        set(value) = _isLoading.postValue(value)

    val isLoading: LiveData<Boolean>
        get() = _isLoading
}
