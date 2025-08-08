package com.example.news_eat_fronted.presentation.model

data class CategoryItem (
    val imgResId: Int,
    val nameResId: Int,
    var isSelected: Boolean? = false
)