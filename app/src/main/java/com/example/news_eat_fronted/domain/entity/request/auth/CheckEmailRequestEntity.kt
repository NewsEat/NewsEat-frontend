package com.example.news_eat_fronted.domain.entity.request.auth

import com.example.news_eat_fronted.data.model.request.auth.CheckEmailRequestDto

data class CheckEmailRequestEntity (
    val emailAuthId: Int,
    val emailAuthCode: String
) {
    fun toCheckEmailRequestDto() = CheckEmailRequestDto(
        emailAuthId = emailAuthId,
        emailAuthCode = emailAuthCode
    )
}