package com.example.news_eat_fronted.data.datasourceImpl

import com.example.news_eat_fronted.data.datasource.AuthRemoteDataSource
import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.auth.CheckEmailRequestDto
import com.example.news_eat_fronted.data.model.request.auth.LoginRequestDto
import com.example.news_eat_fronted.data.model.request.auth.SendEmailRequestDto
import com.example.news_eat_fronted.data.model.request.auth.SignupRequestDto
import com.example.news_eat_fronted.data.model.response.auth.CheckEmailResponseDto
import com.example.news_eat_fronted.data.model.response.auth.LoginResponseDto
import com.example.news_eat_fronted.data.model.response.auth.SendEmailResponseDto
import com.example.news_eat_fronted.data.model.response.auth.SignupResponseDto
import com.example.news_eat_fronted.data.service.AuthService
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authService: AuthService
): AuthRemoteDataSource {
    override suspend fun sendEmail(sendEmailRequestDto: SendEmailRequestDto): BaseResponse<SendEmailResponseDto>
    = authService.sendEmail(sendEmailRequestDto)

    override suspend fun checkEmail(checkEmailRequestDto: CheckEmailRequestDto): BaseResponse<CheckEmailResponseDto>
    = authService.checkEmail(checkEmailRequestDto)

    override suspend fun signup(signupRequestDto: SignupRequestDto): BaseResponse<SignupResponseDto>
    = authService.signup(signupRequestDto)

    override suspend fun login(loginRequestDto: LoginRequestDto): BaseResponse<LoginResponseDto>
    = authService.login(loginRequestDto)
}