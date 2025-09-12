package com.example.news_eat_fronted.domain.entity.request.news

import com.example.news_eat_fronted.data.model.request.news.GetCategoryNewsRequestDto

data class GetCategoryNewsRequestEntity (
    val category: String,
    val lastNewsId: Long,
    val size: Int
) {
    fun toGetCategoryNewsRequestDto() = GetCategoryNewsRequestDto(
        category = category,
        lastNewsId = lastNewsId,
        size = size
    )
}