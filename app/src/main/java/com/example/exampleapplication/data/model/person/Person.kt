package com.example.exampleapplication.data.model.person

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "persons",
    foreignKeys = [
        ForeignKey(entity = Address::class, parentColumns = ["id"], childColumns = ["address_id"]),
        ForeignKey(entity = Company::class, parentColumns = ["id"], childColumns = ["company_id"])
    ]
)
data class Person(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
    val address_id: Int,
    val company_id: Int
)
