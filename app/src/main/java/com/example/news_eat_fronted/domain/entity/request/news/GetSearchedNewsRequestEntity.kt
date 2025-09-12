package com.example.news_eat_fronted.domain.entity.request.news

import com.example.news_eat_fronted.data.model.request.news.GetSearchedNewsRequestDto

data class GetSearchedNewsRequestEntity(
    val keyword: String,
    val lastNewsId: Long,
    val size: Int
) {
    fun toGetSearchedNewsRequestDto() = GetSearchedNewsRequestDto(
        keyword = keyword,
        lastNewsId = lastNewsId,
        size = size
    )
}
