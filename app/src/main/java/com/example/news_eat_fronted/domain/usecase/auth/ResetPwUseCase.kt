package com.example.news_eat_fronted.domain.usecase.auth

import com.example.news_eat_fronted.domain.entity.request.user.ModifyPwRequestEntity
import com.example.news_eat_fronted.domain.repository.AuthRepository
import javax.inject.Inject

class ResetPwUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(modifyPwRequestEntity: ModifyPwRequestEntity)
    = authRepository.resetPw(modifyPwRequestEntity)
}