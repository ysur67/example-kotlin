package com.example.exampleapplication.data.model.person

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company")
data class Company(
    @PrimaryKey val id: Int,
    val name: String,
    @ColumnInfo(name = "catch_phrase") val catchPhrase: String,
    val bs: String
)
