package com.example.news_eat_fronted.domain.entity.request.news

data class NewsSummaryResponseEntity(
    val title: String,
    val sentiment: String,
    val summaryResult: String
)
