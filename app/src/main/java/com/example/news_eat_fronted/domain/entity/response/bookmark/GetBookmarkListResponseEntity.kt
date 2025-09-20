package com.example.news_eat_fronted.domain.entity.response.bookmark

data class GetBookmarkListResponseEntity(
    val bookmarkResponseList: List<BookmarkResponseEntity>,
    val hasNext: Boolean
)

data class BookmarkResponseEntity(
    val bookmarkId: Long,
    val title: String,
    val category: String,
    val imgUrl: String,
    val publishedAt: String,
    val newsDeleted: Boolean
)