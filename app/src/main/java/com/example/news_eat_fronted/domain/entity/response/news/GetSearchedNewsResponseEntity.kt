package com.example.news_eat_fronted.domain.entity.response.news

data class GetSearchedNewsResponseEntity(
    val searchNewsResponses: List<CategoryNewsResponseEntity>,
    val hasNext: Boolean
)