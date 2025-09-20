package com.example.news_eat_fronted.domain.entity.response.bookmark

data class GetBookmarkedNewsDetailResponseEntity (
    val bookmarkId: Long,
    val title: String,
    val content: String,
    val publisher: String,
    val sentiment: String,
    val publishedAt: String,
    val imgUrl: String,
    val category: String,
    val newsDeleted: Boolean
)