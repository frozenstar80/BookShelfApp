package com.example.bookshelfapp.domain.api

import okhttp3.ResponseBody
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        loading<T>()
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}",response.errorBody())
        } catch (e: Exception) {
            return error(e.message ?: e.toString(),null)
        }
    }

    private fun <T> error(errorMessage: String,json : ResponseBody?): NetworkResult<T> {
        return if (json !=null) NetworkResult.Error("Api call failed $errorMessage", json)
        else NetworkResult.Error("Api call failed $errorMessage", null)

    }

}

private fun <T> loading(): NetworkResult<T> =
    NetworkResult.Loading()
