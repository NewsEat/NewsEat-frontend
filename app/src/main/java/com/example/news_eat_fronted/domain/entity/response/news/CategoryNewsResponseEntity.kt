package com.example.news_eat_fronted.domain.entity.response.news

data class CategoryNewsResponseEntity(
    val newsId: Long,
    val title: String,
    val imgUrl: String,
    val publisher: String,
    val publishedAt: String,
)