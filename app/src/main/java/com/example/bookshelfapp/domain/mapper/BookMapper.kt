package com.example.bookshelfapp.domain.mapper

import com.example.bookshelfapp.data.local.BookEntity
import com.example.bookshelfapp.data.remote.Book

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = this.id ?: "",
        image = this.image,
        hits = this.hits,
        alias = this.alias,
        title = this.title,
        lastChapterDate = this.lastChapterDate,
        isFavourite = false // Default value for isFavourite
    )
}

fun List<Book>.toBookEntityList(): List<BookEntity> {
    return this.map { it.toBookEntity() }
}