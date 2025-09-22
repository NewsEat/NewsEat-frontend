package com.example.news_eat_fronted.data.model.request.user

import com.google.gson.annotations.SerializedName

data class UpdateNicknameRequestDto(
    @SerializedName("nickname")
    val nickname: String
)