package com.example.news_eat_fronted.domain.usecase.auth

import com.example.news_eat_fronted.domain.entity.request.auth.VerifyResetPwRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.VerifyResetPwResponseEntity
import com.example.news_eat_fronted.domain.repository.AuthRepository
import javax.inject.Inject

class VerifyResetPwUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(verifyResetPwRequestEntity: VerifyResetPwRequestEntity): VerifyResetPwResponseEntity
    = authRepository.verifyResetPw(verifyResetPwRequestEntity)
}