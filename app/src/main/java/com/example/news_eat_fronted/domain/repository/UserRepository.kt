package com.example.news_eat_fronted.domain.repository

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.domain.entity.request.user.SetDetoxModeRequestEntity
import com.example.news_eat_fronted.domain.entity.request.user.UpdateNicknameRequestEntity
import com.example.news_eat_fronted.domain.entity.response.user.GetMyPageProfileResponseEntity
import com.example.news_eat_fronted.domain.entity.response.user.GetNicknameResponseEntity
import com.example.news_eat_fronted.domain.entity.response.user.SetDetoxModeResponseEntity

interface UserRepository {
    suspend fun withdraw()

    suspend fun setDetoxMode(setDetoxModeRequestEntity: SetDetoxModeRequestEntity): SetDetoxModeResponseEntity

    suspend fun getMyPageProfile(): GetMyPageProfileResponseEntity

    suspend fun updateNickname(updateNicknameRequestEntity: UpdateNicknameRequestEntity) : UpdateNicknameRequestEntity

    suspend fun getNickname(): GetNicknameResponseEntity
}