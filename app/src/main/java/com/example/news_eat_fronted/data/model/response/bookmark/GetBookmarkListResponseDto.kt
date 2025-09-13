package com.example.news_eat_fronted.data.model.response.bookmark

import com.example.news_eat_fronted.domain.entity.response.bookmark.BookmarkResponseEntity
import com.example.news_eat_fronted.domain.entity.response.bookmark.GetBookmarkListResponseEntity
import com.google.gson.annotations.SerializedName

data class GetBookmarkListResponseDto(
    @SerializedName("bookmarkResponseList")
    val bookmarkResponseList: List<BookmarkResponseDto>,
    @SerializedName("hasNext")
    val hasNext: Boolean
) {
    data class BookmarkResponseDto (
        @SerializedName("bookmarkId")
        val bookmarkId: Long,
        @SerializedName("title")
        val title: String,
        @SerializedName("category")
        val category: String,
        @SerializedName("imgUrl")
        val imgUrl: String,
        @SerializedName("publishedAt")
        val publishedAt: String
    ) {
        fun toBookmarkResponseEntity() = BookmarkResponseEntity(
            bookmarkId = bookmarkId,
            title = title,
            category = category,
            imgUrl = imgUrl,
            publishedAt = publishedAt
        )
    }

    fun toGetBookmarkListResponseEntity() = GetBookmarkListResponseEntity(
        bookmarkResponseList = bookmarkResponseList.map { it.toBookmarkResponseEntity() },
        hasNext = hasNext
    )
}
