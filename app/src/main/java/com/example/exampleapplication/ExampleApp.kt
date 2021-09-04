package com.example.exampleapplication

import android.app.Application
import com.example.exampleapplication.di.component.AppComponent
import com.example.exampleapplication.di.component.DaggerAppComponent
import com.example.exampleapplication.di.module.AppModule

class ExampleApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}
