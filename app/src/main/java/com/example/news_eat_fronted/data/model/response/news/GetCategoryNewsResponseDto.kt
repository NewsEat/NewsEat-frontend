package com.example.news_eat_fronted.data.model.response.news

import com.example.news_eat_fronted.domain.entity.response.news.CategoryNewsResponseEntity
import com.example.news_eat_fronted.domain.entity.response.news.GetCategoryNewsResponseEntity
import com.google.gson.annotations.SerializedName

data class GetCategoryNewsResponseDto(
    @SerializedName("categoryNewsResponses")
    val categoryNewsResponses: List<CategoryNewsResponseDto>,
    @SerializedName("hasNext")
    val hasNext: Boolean
) {
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
        val publishedAt: String?,
        @SerializedName("content")
        val content: String?
    ) {
        fun toCategoryNewsResponseEntity() = CategoryNewsResponseEntity(
            newsId = newsId,
            title = title,
            imgUrl = imgUrl,
            publisher = publisher,
            publishedAt = publishedAt,
            content = content
        )
    }

    fun toGetCategoryNewsResponseEntity() = GetCategoryNewsResponseEntity(
        categoryNewsResponses = categoryNewsResponses.map { it.toCategoryNewsResponseEntity() },
        hasNext = hasNext
    )
}