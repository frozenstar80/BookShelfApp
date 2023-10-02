package com.example.bookshelfapp.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookshelfapp.data.local.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM book")
    fun getAllBooks(): Flow<List<BookEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(location: BookEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBookDetails(location: BookEntity)


}