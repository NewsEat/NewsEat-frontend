package com.example.news_eat_fronted.domain.entity.response.news

data class GetRecommendationsResponseEntity(
    val suggestedNewsResponses: List<RecommendationsResponseEntity>
)

data class RecommendationsResponseEntity(
    val newsId: Long,
    val imgUrl: String,
    val title: String
)