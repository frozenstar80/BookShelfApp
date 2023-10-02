package com.example.bookshelfapp.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.data.local.UserCredentials

@Database(entities = [BookEntity::class,UserCredentials::class], version = 2, exportSchema = false)
abstract class BookDatabase  : RoomDatabase(){
    abstract fun dao(): Dao
}