package com.example.news_eat_fronted.data.model.response.auth

import com.example.news_eat_fronted.domain.entity.response.auth.VerifyResetPwResponseEntity
import com.google.gson.annotations.SerializedName

data class VerifyResetPwResponseDto(
    @SerializedName("userId")
    val userId: Int
) {
    fun toVerifyResetPwResponseEntity() = VerifyResetPwResponseEntity(
        userId = userId
    )
}