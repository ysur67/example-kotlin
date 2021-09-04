package com.example.exampleapplication.di.component

import com.example.exampleapplication.MainActivity
import com.example.exampleapplication.di.module.DataSourceModule
import com.example.exampleapplication.di.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataSourceModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
