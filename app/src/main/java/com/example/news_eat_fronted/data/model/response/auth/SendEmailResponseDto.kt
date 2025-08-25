package com.example.news_eat_fronted.data.model.response.auth

import com.example.news_eat_fronted.domain.entity.response.auth.SendEmailResponseEntity
import com.google.gson.annotations.SerializedName

data class SendEmailResponseDto (
    @SerializedName("emailAuthId")
    val emailAuthId: Int,
    @SerializedName("createDate")
    val createDate: String
) {
    fun toSendEmailResponseEntity() = SendEmailResponseEntity(
        emailAuthId = emailAuthId,
        createDate = createDate
    )
}