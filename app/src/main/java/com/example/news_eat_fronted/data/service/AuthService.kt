package com.example.news_eat_fronted.data.service

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.auth.SendEmailRequestDto
import com.example.news_eat_fronted.data.model.response.auth.SendEmailResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/email")
    suspend fun sendEmail(
        @Body sendEmailRequestDto: SendEmailRequestDto
    ): BaseResponse<SendEmailResponseDto>
}