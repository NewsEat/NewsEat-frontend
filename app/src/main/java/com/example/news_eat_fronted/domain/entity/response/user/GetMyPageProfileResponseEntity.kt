package com.example.news_eat_fronted.domain.entity.response.user

data class GetMyPageProfileResponseEntity (
    val nickname: String,
    val categories: List<String>
)