package com.example.news_eat_fronted.data.model.response.user

import com.example.news_eat_fronted.domain.entity.response.user.GetNicknameResponseEntity
import com.google.gson.annotations.SerializedName

data class GetNicknameResponseDto (
    @SerializedName("nickname")
    val nickname: String
) {
    fun toGetNicknameResponseEntity() = GetNicknameResponseEntity(
        nickname = nickname
    )
}