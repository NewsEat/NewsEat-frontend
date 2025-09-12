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
    fun toGetCategoryNewsResponseEntity() = GetCategoryNewsResponseEntity(
        categoryNewsResponses = categoryNewsResponses.map { it.toCategoryNewsResponseEntity() },
        hasNext = hasNext
    )
}