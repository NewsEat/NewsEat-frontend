package com.example.news_eat_fronted.data.datasourceImpl

import com.example.news_eat_fronted.data.datasource.UserRemoteDataSource
import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.user.SetDetoxModeRequestDto
import com.example.news_eat_fronted.data.model.response.user.GetMyPageProfileResponseDto
import com.example.news_eat_fronted.data.model.response.user.SetDetoxModeResponseDto
import com.example.news_eat_fronted.data.service.UserService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
): UserRemoteDataSource {

    override suspend fun withdraw(): BaseResponse<Unit>
    = userService.withdraw()

    override suspend fun setDetoxMode(setDetoxModeRequestDto: SetDetoxModeRequestDto): BaseResponse<SetDetoxModeResponseDto>
    = userService.setDetoxMod(setDetoxModeRequestDto)

    override suspend fun getMyPageProfile(): BaseResponse<GetMyPageProfileResponseDto>
    = userService.getMyPageProfile()
}