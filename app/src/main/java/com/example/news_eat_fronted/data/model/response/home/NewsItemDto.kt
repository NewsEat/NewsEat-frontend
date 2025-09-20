package com.example.news_eat_fronted.data.model.response.home

import com.example.news_eat_fronted.domain.entity.response.home.NewsItemEntity
import com.google.gson.annotations.SerializedName

data class NewsItemDto(
    @SerializedName("newsId")
    val newsId: Long,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("title")
    val title: String
) {
    fun toNewsItemEntity() = NewsItemEntity(
        newsId = newsId,
        imgUrl = imgUrl,
        title = title
    )
}
