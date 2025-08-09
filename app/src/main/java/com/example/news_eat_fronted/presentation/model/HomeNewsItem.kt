package com.example.news_eat_fronted.presentation.model

data class HomeNewsItem(
    val id: Int,
    val imgResId: String,
    val title: String,
    val publisher: String? = null,
    val date: String? = null
)