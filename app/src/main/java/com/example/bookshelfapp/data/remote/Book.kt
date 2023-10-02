package com.example.bookshelfapp.data.remote

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("id"              ) var id              : String? = null,
    @SerializedName("image"           ) var image           : String? = null,
    @SerializedName("hits"            ) var hits            : Int?    = null,
    @SerializedName("alias"           ) var alias           : String? = null,
    @SerializedName("title"           ) var title           : String? = null,
    @SerializedName("lastChapterDate" ) var lastChapterDate : Int?    = null
)
