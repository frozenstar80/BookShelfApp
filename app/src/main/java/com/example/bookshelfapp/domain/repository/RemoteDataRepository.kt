package com.example.bookshelfapp.domain.repository


import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.data.local.UserCredentials
import kotlinx.coroutines.flow.Flow

interface RemoteDataRepository {

    //Handle Database Operations

    suspend fun updateBookData(bookEntity: BookEntity) : Boolean
    suspend fun insertBookData(bookEntity: BookEntity) : Boolean
    suspend fun getAllBooks() : Flow<List<BookEntity>>
    suspend fun insertUser(userCredentials: UserCredentials) : Boolean
    suspend fun checkCredential(email:String,password:String) : Flow<List<UserCredentials>>?

}