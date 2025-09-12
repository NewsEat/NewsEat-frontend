package com.example.news_eat_fronted.data.model.response.news

import com.example.news_eat_fronted.domain.entity.response.news.CategoryNewsResponseEntity
import com.google.gson.annotations.SerializedName

data class CategoryNewsResponseDto(
    @SerializedName("newsId")
    val newsId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
) {
    fun toCategoryNewsResponseEntity() = CategoryNewsResponseEntity(
        newsId = newsId,
        title = title,
        imgUrl = imgUrl,
        publisher = publisher,
        publishedAt = publishedAt
    )
}