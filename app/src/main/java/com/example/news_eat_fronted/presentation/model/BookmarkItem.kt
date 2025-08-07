package com.example.news_eat_fronted.presentation.model

data class BookmarkItem (
    val title: String,
    val category: String,
    val date: String,
    val thumbnailUrl: String,
    var isBookmarked: Boolean = true
)