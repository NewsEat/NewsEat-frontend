package com.example.news_eat_fronted.data.model.response.auth

import com.example.news_eat_fronted.domain.entity.response.auth.CheckEmailResponseEntity
import com.google.gson.annotations.SerializedName

data class CheckEmailResponseDto (
    @SerializedName("isChecked")
    val isChecked: Boolean
) {
    fun toCheckEmailResponseEntity() = CheckEmailResponseEntity(
        isChecked = isChecked
    )
}