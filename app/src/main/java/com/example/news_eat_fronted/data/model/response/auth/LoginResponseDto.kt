package com.example.news_eat_fronted.data.model.response.auth

import com.example.news_eat_fronted.domain.entity.response.auth.LoginResponseEntity
import com.google.gson.annotations.SerializedName

data class LoginResponseDto (
    @SerializedName("role")
    val role: String,
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
) {
    fun toLoginResponseEntity() = LoginResponseEntity(
        role = role,
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}