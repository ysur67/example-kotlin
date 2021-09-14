package com.example.exampleapplication.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.exampleapplication.data.model.person.Person


data class PostWithPerson(
    @Embedded val post: Post,
    @Relation(
        parentColumn = "user_id",
        entityColumn = "id"
    )
    val person: Person
)
