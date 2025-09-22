package com.example.news_eat_fronted.data.datasource

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.user.SetDetoxModeRequestDto
import com.example.news_eat_fronted.data.model.response.user.GetNicknameResponseDto
import com.example.news_eat_fronted.data.model.response.user.SetDetoxModeResponseDto

interface UserRemoteDataSource {
    suspend fun withdraw(): BaseResponse<Unit>

    suspend fun setDetoxMode(setDetoxModeRequestDto: SetDetoxModeRequestDto): BaseResponse<SetDetoxModeResponseDto>

    suspend fun getNickname(): BaseResponse<GetNicknameResponseDto>
}