package com.example.bookshelfapp.domain.api

import com.example.bookshelfapp.data.remote.Book
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("b/ZEDF")
    suspend fun getBooks(): Response<List<Book>>

}