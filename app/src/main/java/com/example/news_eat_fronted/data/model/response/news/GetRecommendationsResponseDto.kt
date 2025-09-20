package com.example.news_eat_fronted.data.model.response.news

import com.example.news_eat_fronted.domain.entity.response.news.GetRecommendationsResponseEntity
import com.example.news_eat_fronted.domain.entity.response.news.RecommendationsResponseEntity
import com.google.gson.annotations.SerializedName

data class GetRecommendationsResponseDto(
    @SerializedName("suggestedNewsResponses")
    val suggestedNewsResponses: List<RecommendationsResponseDto>
) {
    data class RecommendationsResponseDto(
        @SerializedName("newsId")
        val newsId: Long,
        @SerializedName("imgUrl")
        val imgUrl: String,
        @SerializedName("title")
        val title: String
    ) {
        fun toRecommendationsResponseEntity() = RecommendationsResponseEntity(
            newsId = newsId,
            imgUrl = imgUrl,
            title = title
        )
    }

    fun toGetRecommendationsResponseEntity() = GetRecommendationsResponseEntity(
        suggestedNewsResponses = suggestedNewsResponses.map { it.toRecommendationsResponseEntity() }
    )
}
