package com.example.news_eat_fronted.domain.entity.request.user

import com.example.news_eat_fronted.data.model.request.user.ModifyPwRequestDto

data class ModifyPwRequestEntity(
    val password: String,
    val confirmPassword: String
) {
    fun toModifyPwRequestDto() = ModifyPwRequestDto(
        password = password,
        confirmPassword = confirmPassword
    )
}