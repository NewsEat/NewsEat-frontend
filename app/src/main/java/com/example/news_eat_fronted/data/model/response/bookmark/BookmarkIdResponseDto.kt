package com.example.news_eat_fronted.data.model.response.bookmark

import com.example.news_eat_fronted.domain.entity.response.bookmark.BookmarkIdResponseEntity
import com.google.gson.annotations.SerializedName

data class BookmarkIdResponseDto(
    @SerializedName("bookmarkId")
    val bookmarkId: Long
) {
    fun toBookmarkResponseEntity() = BookmarkIdResponseEntity(
        bookmarkId = bookmarkId
    )
}