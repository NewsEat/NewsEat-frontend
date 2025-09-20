package com.example.news_eat_fronted.domain.entity.response.home

data class GetHomeNewsSectionResponseEntity (
    val isDetox: Boolean,
    val sections: List<SectionEntity>
)

data class SectionEntity(
    val type: String,
    val title: String,
    val newsList: List<NewsItemEntity>
)

data class NewsItemEntity(
    val newsId: Long,
    val imgUrl: String,
    val title: String
)