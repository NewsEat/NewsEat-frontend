package com.example.news_eat_fronted.domain.entity.response.news

data class GetCategoryNewsResponseEntity(
    val categoryNewsResponses: List<CategoryNewsResponseEntity>,
    val hasNext: Boolean
)