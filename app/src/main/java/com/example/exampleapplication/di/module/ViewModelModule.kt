package com.example.exampleapplication.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.exampleapplication.di.ViewModelFactory
import com.example.exampleapplication.di.ViewModelKey
import com.example.exampleapplication.domain.implementation.PersonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PersonViewModel::class)
    abstract fun bindPersonViewModel(viewModel: PersonViewModel) : ViewModel
}
