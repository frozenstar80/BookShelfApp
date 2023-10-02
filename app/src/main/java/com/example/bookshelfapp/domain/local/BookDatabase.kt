package com.example.bookshelfapp.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookshelfapp.data.local.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDatabase  : RoomDatabase(){
    abstract fun dao(): Dao
}