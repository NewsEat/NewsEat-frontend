package com.example.news_eat_fronted.domain.entity.response.auth

data class LoginResponseEntity (
    val role: String,
    val accessToken: String,
    val refreshToken: String
)