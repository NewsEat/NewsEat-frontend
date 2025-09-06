package com.example.news_eat_fronted.domain.repository

import com.example.news_eat_fronted.domain.entity.request.auth.CheckEmailRequestEntity
import com.example.news_eat_fronted.domain.entity.request.auth.LoginRequestEntity
import com.example.news_eat_fronted.domain.entity.request.auth.SendEmailRequestEntity
import com.example.news_eat_fronted.domain.entity.request.auth.SignupRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.CheckEmailResponseEntity
import com.example.news_eat_fronted.domain.entity.response.auth.LoginResponseEntity
import com.example.news_eat_fronted.domain.entity.response.auth.SendEmailResponseEntity
import com.example.news_eat_fronted.domain.entity.response.auth.SignupResponseEntity

interface AuthRepository {
    suspend fun sendEmail(sendEmailRequestEntity: SendEmailRequestEntity): SendEmailResponseEntity

    suspend fun checkEmail(checkEmailRequestEntity: CheckEmailRequestEntity): CheckEmailResponseEntity

    suspend fun signup(signupRequestEntity: SignupRequestEntity): SignupResponseEntity

    suspend fun login(loginRequestEntity: LoginRequestEntity): LoginResponseEntity

    suspend fun reissueToken(refreshToken: String): LoginResponseEntity
}