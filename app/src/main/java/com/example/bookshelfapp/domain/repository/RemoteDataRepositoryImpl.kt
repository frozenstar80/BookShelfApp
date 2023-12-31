package com.example.bookshelfapp.domain.repository


import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.data.local.UserCredentials
import com.example.bookshelfapp.domain.local.BookDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

 class RemoteDataRepositoryImpl @Inject constructor(
    private val db: BookDatabase
) : RemoteDataRepository {
     override suspend fun getAllBooks(): Flow<List<BookEntity>> {
         return  db.dao().getAllBooks()
     }

     override suspend fun checkCredential(email: String, password: String): Flow<List<UserCredentials>> {
        return db.dao().getUserByCredentials(email, password)
     }

     override suspend fun insertBookData(bookEntity: BookEntity): Boolean {
         return try {
             db.dao().insertBook(bookEntity)
             true
         } catch (e: Exception) {
             e.printStackTrace()
             false
         }
     }

     override suspend fun insertUser(userCredentials: UserCredentials): Boolean {
         return try {
             db.dao().insertUser(userCredentials)
             true
         } catch (e: Exception) {
             e.printStackTrace()
             false
         }
     }

     override suspend fun updateBookData(bookEntity: BookEntity): Boolean {
         return try {
             db.dao().updateBookDetails(bookEntity)
             true
         } catch (e: Exception) {
             e.printStackTrace()
             false
         }
     }


}