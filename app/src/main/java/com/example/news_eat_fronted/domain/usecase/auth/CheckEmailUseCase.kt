package com.example.news_eat_fronted.domain.usecase.auth

import com.example.news_eat_fronted.domain.entity.request.auth.CheckEmailRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.CheckEmailResponseEntity
import com.example.news_eat_fronted.domain.repository.AuthRepository

class CheckEmailUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(checkEmailRequestEntity: CheckEmailRequestEntity): CheckEmailResponseEntity
    = authRepository.checkEmail(checkEmailRequestEntity)
}