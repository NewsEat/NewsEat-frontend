package com.example.news_eat_fronted.data.model.response.news

import com.example.news_eat_fronted.domain.entity.request.news.GetNewsDetailResponseEntity
import com.google.gson.annotations.SerializedName

data class GetNewsDetailResponseDto (
    @SerializedName("newsId")
    val newsId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("sentiment")
    val sentiment: String,
    @SerializedName("isBookmarked")
    val isBookmarked: Boolean
) {
    fun toGetNewsDetailResponseEntity() = GetNewsDetailResponseEntity(
        newsId = newsId,
        title = title,
        content = content,
        imgUrl = imgUrl,
        publisher = publisher,
        publishedAt = publishedAt,
        category = category,
        sentiment = sentiment,
        isBookmarked = isBookmarked
    )
}