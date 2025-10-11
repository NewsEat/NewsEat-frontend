package com.example.news_eat_fronted.domain.entity.request.auth

import com.example.news_eat_fronted.data.model.request.auth.VerifyResetPwRequestDto

data class VerifyResetPwRequestEntity(
    val emailAuthId: Long
) {
    fun toVerifyResetPwRequestDto() = VerifyResetPwRequestDto(
        emailAuthId = emailAuthId
    )
}
