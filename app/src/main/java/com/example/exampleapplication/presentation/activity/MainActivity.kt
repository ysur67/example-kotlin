package com.example.exampleapplication.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.exampleapplication.ExampleApp
import com.example.exampleapplication.R
import com.example.exampleapplication.di.ViewModelFactory
import com.example.exampleapplication.domain.implementation.PersonViewModel
import com.example.exampleapplication.utils.activity.makeToast
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PersonViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ExampleApp).appComponent.inject(this)
        setContentView(R.layout.activity_main)
//        viewModel.updatePersons()
        viewModel.updatePosts()
    }
}
