package com.example.news_eat_fronted.domain.usecase.auth

import com.example.news_eat_fronted.domain.entity.request.auth.SendEmailRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.SendEmailResponseEntity
import com.example.news_eat_fronted.domain.repository.AuthRepository

class SendEmailUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(sendEmailRequestEntity: SendEmailRequestEntity): SendEmailResponseEntity
    =authRepository.sendEmail(sendEmailRequestEntity)
}