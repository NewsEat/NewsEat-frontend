package com.example.news_eat_fronted.data.service

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.user.SetDetoxModeRequestDto
import com.example.news_eat_fronted.data.model.response.user.GetMyPageProfileResponseDto
import com.example.news_eat_fronted.data.model.response.user.SetDetoxModeResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserService {
    @DELETE("auth/withdraw")
    suspend fun withdraw(): BaseResponse<Unit>

    @PUT("member/detox-mode")
    suspend fun setDetoxMod(
        @Body setDetoxModeRequestDto: SetDetoxModeRequestDto
    ): BaseResponse<SetDetoxModeResponseDto>

    @GET("member/profile")
    suspend fun getMyPageProfile() : BaseResponse<GetMyPageProfileResponseDto>
}