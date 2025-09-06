package com.example.news_eat_fronted.data.model.request.auth

import com.google.gson.annotations.SerializedName

data class CheckEmailRequestDto (
    @SerializedName("emailAuthId")
    val emailAuthId: Int,
    @SerializedName("emailAuthCode")
    val emailAuthCode: String,
)