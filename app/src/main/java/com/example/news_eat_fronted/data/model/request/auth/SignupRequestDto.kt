package com.example.news_eat_fronted.data.model.request.auth

import com.google.gson.annotations.SerializedName

data class SignupRequestDto(
    @SerializedName("emailAuthId")
    val emailAuthId: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("categoryIds")
    val categoryIds: List<Int>
)