package com.example.news_eat_fronted.domain.usecase.auth

import com.example.news_eat_fronted.domain.entity.request.auth.SignupRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.SignupResponseEntity
import com.example.news_eat_fronted.domain.repository.AuthRepository

class SignupUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(signupRequestEntity: SignupRequestEntity): SignupResponseEntity
    = authRepository.signup(signupRequestEntity)
}