package com.example.bookshelfapp.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "book")
@Parcelize
data class BookEntity(
    @PrimaryKey(autoGenerate = false)
    var id              : String ,
    var image           : String? ,
    var hits            : Long?    ,
    var alias           : String? ,
    var title           : String? ,
    var lastChapterDate : Long?    ,
    var isFavourite : Boolean = false
) : Parcelable

//Local Data Class to Store Book Info
// @Parcelize is used to send the objects of This Class From One Screen to Another

