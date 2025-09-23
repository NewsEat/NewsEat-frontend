package com.example.news_eat_fronted.domain.entity.request.user

import com.example.news_eat_fronted.data.model.request.user.UpdateNicknameRequestDto

data class UpdateNicknameRequestEntity (
    val nickname: String
) {
    fun toUpdateNicknameRequestDto() = UpdateNicknameRequestDto(
        nickname = nickname
    )
}