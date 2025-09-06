package com.example.news_eat_fronted.domain.entity.request.auth

import com.example.news_eat_fronted.data.model.request.auth.LoginRequestDto

data class LoginRequestEntity (
    val email: String,
    val password: String
) {
    fun toLoginRequestDto() = LoginRequestDto(
        email = email,
        password = password
    )
}