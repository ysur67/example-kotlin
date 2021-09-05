package com.example.exampleapplication.di.component

import com.example.exampleapplication.presentation.activity.MainActivity
import com.example.exampleapplication.di.module.DataSourceModule
import com.example.exampleapplication.di.module.RepositoryModule
import com.example.exampleapplication.di.module.ViewModelModule
import com.example.exampleapplication.presentation.fragment.PostListFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataSourceModule::class, RepositoryModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {
    // Activity
    fun inject(mainActivity: MainActivity)

    // Fragment
    fun inject(postListFragment: PostListFragment)
}
