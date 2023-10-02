package com.example.bookshelfapp.domain.repository

import com.example.bookshelfapp.data.remote.Book
import com.example.bookshelfapp.domain.api.ApiService
import com.example.bookshelfapp.domain.api.BaseApiResponse
import com.example.bookshelfapp.domain.api.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService)  : BaseApiResponse(){

    suspend fun getBooksList(): Flow<NetworkResult<List<Book>>> = flow {
        val response = apiService.getBooks()
        emit(safeApiCall{response})
    }.flowOn(Dispatchers.IO)
//repository class to handle api calling

}