package com.example.bookshelfapp.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.data.local.UserCredentials
import kotlinx.coroutines.flow.Flow

//DAO Class for SQL Queries

@Dao
interface Dao {
    @Query("SELECT * FROM book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(location: BookEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBookDetails(location: BookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userCredentials: UserCredentials)

    @Query("SELECT * FROM user WHERE userName = :userName AND password = :password")
     fun getUserByCredentials(userName: String, password: String): Flow<List<UserCredentials>>


}