package com.example.news_eat_fronted.data.datasource

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.auth.CheckEmailRequestDto
import com.example.news_eat_fronted.data.model.request.auth.SendEmailRequestDto
import com.example.news_eat_fronted.data.model.request.auth.SignupRequestDto
import com.example.news_eat_fronted.data.model.response.auth.CheckEmailResponseDto
import com.example.news_eat_fronted.data.model.response.auth.SendEmailResponseDto
import com.example.news_eat_fronted.data.model.response.auth.SignupResponseDto

interface AuthRemoteDataSource {
        suspend fun sendEmail(sendEmailRequestDto: SendEmailRequestDto): BaseResponse<SendEmailResponseDto>

        suspend fun checkEmail(checkEmailRequestDto: CheckEmailRequestDto): BaseResponse<CheckEmailResponseDto>

        suspend fun signup(signupRequestDto: SignupRequestDto): BaseResponse<SignupResponseDto>
}
