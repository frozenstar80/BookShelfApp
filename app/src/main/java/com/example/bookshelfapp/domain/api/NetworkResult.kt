package com.example.bookshelfapp.domain.api

import okhttp3.ResponseBody

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null,
    val json: ResponseBody? =null
) {

    class Success<T>(data: T) : NetworkResult<T>(data)

    class Error<T>(message: String, data:ResponseBody?) : NetworkResult<T>(null, message,data)

    class Loading<T> : NetworkResult<T>()

}
