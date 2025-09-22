package com.example.news_eat_fronted.data.datasource

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.user.SetDetoxModeRequestDto
import com.example.news_eat_fronted.data.model.request.user.UpdateNicknameRequestDto
import com.example.news_eat_fronted.data.model.response.user.GetMyPageProfileResponseDto
import com.example.news_eat_fronted.data.model.response.user.GetNicknameResponseDto
import com.example.news_eat_fronted.data.model.response.user.SetDetoxModeResponseDto

interface UserRemoteDataSource {
    suspend fun withdraw(): BaseResponse<Unit>

    suspend fun setDetoxMode(setDetoxModeRequestDto: SetDetoxModeRequestDto): BaseResponse<SetDetoxModeResponseDto>

    suspend fun getMyPageProfile() : BaseResponse<GetMyPageProfileResponseDto>

    suspend fun updateNickname(updateNicknameRequestDto: UpdateNicknameRequestDto) : BaseResponse<Unit>

    suspend fun getNickname(): BaseResponse<GetNicknameResponseDto>
}