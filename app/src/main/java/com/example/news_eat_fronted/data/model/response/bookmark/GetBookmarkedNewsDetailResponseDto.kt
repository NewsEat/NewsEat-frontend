package com.example.news_eat_fronted.data.model.response.bookmark

import com.example.news_eat_fronted.domain.entity.response.bookmark.GetBookmarkedNewsDetailResponseEntity
import com.google.gson.annotations.SerializedName

data class GetBookmarkedNewsDetailResponseDto (
    @SerializedName("bookmarkId")
    val bookmarkId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("sentiment")
    val sentiment: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("category")
    val category: String
) {
    fun toGetBookmarkedNewsDetailResponseEntity() = GetBookmarkedNewsDetailResponseEntity(
        bookmarkId = bookmarkId,
        title = title,
        content = content,
        publisher = publisher,
        sentiment = sentiment,
        publishedAt = publishedAt,
        imgUrl = imgUrl,
        category = category
    )
}