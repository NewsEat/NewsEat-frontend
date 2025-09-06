package com.example.news_eat_fronted.domain.usecase.auth

import com.example.news_eat_fronted.domain.entity.response.auth.LoginResponseEntity
import com.example.news_eat_fronted.domain.repository.AuthRepository

class ReissueTokenUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(refreshToken: String): LoginResponseEntity
    = authRepository.reissueToken(refreshToken)
}