package com.example.news_eat_fronted.data.model.request.auth

import com.google.gson.annotations.SerializedName

data class LoginRequestDto (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)