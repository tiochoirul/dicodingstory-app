package com.example.storyapp.data.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("stories")
data class Stories(
    @PrimaryKey
    val id: String,
    val photoUrl: String,
    val createdAt: String,
    val name: String,
    val description: String,
    val lat: Any?,
    val lon: Any?
)
