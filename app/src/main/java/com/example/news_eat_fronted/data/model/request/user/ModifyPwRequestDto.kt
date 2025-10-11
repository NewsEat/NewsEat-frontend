package com.example.news_eat_fronted.data.model.request.user

import com.google.gson.annotations.SerializedName

data class ModifyPwRequestDto(
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("password")
    val password: String,
    @SerializedName("confirmPassword")
    val confirmPassword: String
)