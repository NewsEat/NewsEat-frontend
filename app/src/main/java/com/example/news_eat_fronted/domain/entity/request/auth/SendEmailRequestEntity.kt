package com.example.news_eat_fronted.domain.entity.request.auth

import com.example.news_eat_fronted.data.model.request.auth.SendEmailRequestDto

data class SendEmailRequestEntity (
    val email: String,
    val purpose: Int
) {
    fun toSendEmailRequestDto() = SendEmailRequestDto(
        email = email,
        purpose = purpose
    )
}