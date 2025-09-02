package com.example.news_eat_fronted.data.service

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.auth.CheckEmailRequestDto
import com.example.news_eat_fronted.data.model.request.auth.LoginRequestDto
import com.example.news_eat_fronted.data.model.request.auth.SendEmailRequestDto
import com.example.news_eat_fronted.data.model.request.auth.SignupRequestDto
import com.example.news_eat_fronted.data.model.response.auth.CheckEmailResponseDto
import com.example.news_eat_fronted.data.model.response.auth.LoginResponseDto
import com.example.news_eat_fronted.data.model.response.auth.SendEmailResponseDto
import com.example.news_eat_fronted.data.model.response.auth.SignupResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/email")
    suspend fun sendEmail(
        @Body sendEmailRequestDto: SendEmailRequestDto
    ): BaseResponse<SendEmailResponseDto>

    @POST("auth/email/check")
    suspend fun checkEmail(
        @Body checkEmailRequestDto: CheckEmailRequestDto
    ): BaseResponse<CheckEmailResponseDto>

    @POST("auth/signup")
    suspend fun signup(
        @Body signupRequestDto: SignupRequestDto
    ): BaseResponse<SignupResponseDto>

    @POST("auth/login")
    suspend fun login(
        @Body loginRequestDto: LoginRequestDto
    ): BaseResponse<LoginResponseDto>
}