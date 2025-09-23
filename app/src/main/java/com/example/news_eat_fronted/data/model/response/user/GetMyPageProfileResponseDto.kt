package com.example.news_eat_fronted.data.model.response.user

import com.example.news_eat_fronted.domain.entity.response.user.GetMyPageProfileResponseEntity
import com.google.gson.annotations.SerializedName

data class GetMyPageProfileResponseDto (
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("categories")
    val categories: List<String>
) {
    fun toGetMyPageProfileResponseEntity() = GetMyPageProfileResponseEntity(
        nickname = nickname,
        categories = categories
    )
}