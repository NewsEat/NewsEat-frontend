package com.example.news_eat_fronted.data.model.response.auth

import com.example.news_eat_fronted.domain.entity.response.auth.SignupResponseEntity
import com.google.gson.annotations.SerializedName

data class SignupResponseDto (
    @SerializedName("userId")
    val userId: Int
) {
    fun toSignupResponseEntity() = SignupResponseEntity(
        userId = userId
    )
}