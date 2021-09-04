package com.example.exampleapplication.data.model.person

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val lat: String,
    val lng: String
)
