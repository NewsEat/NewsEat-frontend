package com.example.news_eat_fronted.data.datasource

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.auth.CheckEmailRequestDto
import com.example.news_eat_fronted.data.model.request.auth.LoginRequestDto
import com.example.news_eat_fronted.data.model.request.auth.SendEmailRequestDto
import com.example.news_eat_fronted.data.model.request.auth.SignupRequestDto
import com.example.news_eat_fronted.data.model.request.auth.VerifyResetPwRequestDto
import com.example.news_eat_fronted.data.model.request.user.ModifyPwRequestDto
import com.example.news_eat_fronted.data.model.response.auth.CheckEmailResponseDto
import com.example.news_eat_fronted.data.model.response.auth.LoginResponseDto
import com.example.news_eat_fronted.data.model.response.auth.SendEmailResponseDto
import com.example.news_eat_fronted.data.model.response.auth.SignupResponseDto
import com.example.news_eat_fronted.data.model.response.auth.VerifyResetPwResponseDto

interface AuthRemoteDataSource {
        suspend fun sendEmail(sendEmailRequestDto: SendEmailRequestDto): BaseResponse<SendEmailResponseDto>

        suspend fun checkEmail(checkEmailRequestDto: CheckEmailRequestDto): BaseResponse<CheckEmailResponseDto>

        suspend fun signup(signupRequestDto: SignupRequestDto): BaseResponse<SignupResponseDto>

        suspend fun login(loginRequestDto: LoginRequestDto): BaseResponse<LoginResponseDto>

        suspend fun reissueToken(refreshToken: String): BaseResponse<LoginResponseDto>

        suspend fun verifyResetPw(verifyResetPwRequestDto: VerifyResetPwRequestDto): BaseResponse<VerifyResetPwResponseDto>

        suspend fun resetPw(modifyPwRequestDto: ModifyPwRequestDto): BaseResponse<Unit>
}
