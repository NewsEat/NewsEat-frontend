package com.example.news_eat_fronted.presentation.model

data class NewsDetailItem(
    val id: Int,
    val imgResId: String,
    val title: String,
    val publisher: String,
    val date: String,
    val category: String,
    val sentiment: String,
    val content: String
)