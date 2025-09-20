package com.example.news_eat_fronted.domain.entity.request.user

import com.example.news_eat_fronted.data.model.request.user.SetDetoxModeRequestDto

data class SetDetoxModeRequestEntity(
    val isDetoxMode: Boolean
) {
    fun toSetDetoxModeRequestDto() = SetDetoxModeRequestDto(
        isDetoxMode = isDetoxMode
    )
}
