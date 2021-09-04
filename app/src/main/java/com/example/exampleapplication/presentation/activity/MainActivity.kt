package com.example.exampleapplication.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exampleapplication.ExampleApp
import com.example.exampleapplication.R
import com.example.exampleapplication.data.repository.PersonRepository
import com.example.exampleapplication.di.module.RepositoryModule
import com.example.exampleapplication.utils.activity.makeToast
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}