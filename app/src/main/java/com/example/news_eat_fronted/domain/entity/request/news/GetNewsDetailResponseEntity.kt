package com.example.news_eat_fronted.domain.entity.request.news

data class GetNewsDetailResponseEntity (
    val newsId: Long,
    val title: String,
    val content: String,
    val imgUrl: String,
    val publisher: String,
    val publishedAt: String,
    val category: String,
    val sentiment: String,
    val isBookmarked: Boolean
)