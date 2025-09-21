package com.example.news_eat_fronted.data.model.response.home

import com.example.news_eat_fronted.domain.entity.response.home.GetLatestNewsResponseEntity
import com.google.gson.annotations.SerializedName

data class GetLatestNewsResponseDto(
    @SerializedName("homeNewsResponses")
    val homeNewsResponses: List<NewsItemDto>
) {
    fun toGetLatestNewsResponseEntity() = GetLatestNewsResponseEntity(
        homeNewsResponses = homeNewsResponses.map { it.toNewsItemEntity() }
    )
}