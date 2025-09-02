package com.example.news_eat_fronted.domain.usecase.auth

import com.example.news_eat_fronted.domain.entity.request.auth.LoginRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.LoginResponseEntity
import com.example.news_eat_fronted.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(loginRequestEntity: LoginRequestEntity): LoginResponseEntity
    = authRepository.login(loginRequestEntity)
}