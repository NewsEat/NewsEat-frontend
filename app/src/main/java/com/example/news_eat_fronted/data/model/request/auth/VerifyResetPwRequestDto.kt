package com.example.news_eat_fronted.data.model.request.auth

import com.google.gson.annotations.SerializedName

data class VerifyResetPwRequestDto(
    @SerializedName("emailAuthId")
    val emailAuthId: Long
)