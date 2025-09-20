package com.example.news_eat_fronted.data.model.response.user

import com.example.news_eat_fronted.domain.entity.response.user.SetDetoxModeResponseEntity
import com.google.gson.annotations.SerializedName

data class SetDetoxModeResponseDto(
    @SerializedName("isDetoxMode")
    val isDetoxMode: Boolean
) {
    fun toSetDetoxModeResponseEntity() = SetDetoxModeResponseEntity(
        isDetoxMode = isDetoxMode
    )
}