package com.example.news_eat_fronted.domain.entity.request.user

import com.example.news_eat_fronted.data.model.request.auth.SignupRequestDto
import com.example.news_eat_fronted.data.model.request.user.UpdateCategoryRequestDto

data class UpdateCategoryRequestEntity (
    val categoryIds : List<Int>
) {
    fun toUpdateCategoryRequestDto() = UpdateCategoryRequestDto(
        categoryIds = categoryIds
    )
}