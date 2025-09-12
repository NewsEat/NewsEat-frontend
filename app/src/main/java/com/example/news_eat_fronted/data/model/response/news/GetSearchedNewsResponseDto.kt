package com.example.news_eat_fronted.data.model.response.news

import com.example.news_eat_fronted.domain.entity.response.news.GetSearchedNewsResponseEntity
import com.google.gson.annotations.SerializedName

data class GetSearchedNewsResponseDto (
    @SerializedName("searchNewsResponses")
    val searchNewsResponses: List<CategoryNewsResponseDto>,
    @SerializedName("hasNext")
    val hasNext: Boolean
) {
    fun toGetSearchedNewsResponseEntity() = GetSearchedNewsResponseEntity(
        searchNewsResponses = searchNewsResponses.map { it.toCategoryNewsResponseEntity() },
        hasNext = hasNext
    )
}