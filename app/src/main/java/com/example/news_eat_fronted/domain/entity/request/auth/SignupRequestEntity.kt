package com.example.news_eat_fronted.domain.entity.request.auth

import com.example.news_eat_fronted.data.model.request.auth.SignupRequestDto

data class SignupRequestEntity (
    val emailAuthId: Int,
    val email: String,
    val password: String,
    val nickname: String,
    val categoryIds: List<Int>
) {
    fun toSignupRequestDto() = SignupRequestDto(
        emailAuthId = emailAuthId,
        email = email,
        password = password,
        nickname = nickname,
        categoryIds = categoryIds
    )
}