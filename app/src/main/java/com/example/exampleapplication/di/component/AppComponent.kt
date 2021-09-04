package com.example.exampleapplication.di.component

import com.example.exampleapplication.MainActivity
import com.example.exampleapplication.di.module.DataSourceModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataSourceModule::class,])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
