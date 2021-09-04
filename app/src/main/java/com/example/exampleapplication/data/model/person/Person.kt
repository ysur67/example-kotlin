package com.example.exampleapplication.data.model.person

import androidx.room.*

@Entity(
    tableName = "persons"
)
data class Person(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    @Embedded val address: Address?,
    @Embedded val company: Company?
)
