package com.example.bookshelfapp.domain.repository


import com.example.bookshelfapp.data.local.BookEntity
import kotlinx.coroutines.flow.Flow

interface RemoteDataRepository {

    suspend fun updateBookData(bookEntity: BookEntity) : Boolean
    suspend fun insertBookData(bookEntity: BookEntity) : Boolean
    suspend fun getAllBooks() : Flow<List<BookEntity>>

}