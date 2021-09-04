package com.example.exampleapplication.data.model.person

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Company(
    val title: String,
    @ColumnInfo(name = "catch_phrase") val catchPhrase: String,
    val bs: String
)
