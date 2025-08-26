package com.example.news_eat_fronted.domain.repository

import com.example.news_eat_fronted.domain.entity.request.auth.SendEmailRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.SendEmailResponseEntity

interface AuthRepository {
    suspend fun sendEmail(sendEmailRequestEntity: SendEmailRequestEntity): SendEmailResponseEntity
}