package com.example.bookshelfapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserCredentials(
    @PrimaryKey(autoGenerate = true)
    val id : Int =0,
    val userName :String,
    val password : String,
    val country : String
)
