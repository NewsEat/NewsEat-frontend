package com.example.news_eat_fronted.data.repositoryImpl

import com.example.news_eat_fronted.data.datasource.AuthRemoteDataSource
import com.example.news_eat_fronted.domain.entity.request.auth.SendEmailRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.SendEmailResponseEntity
import com.example.news_eat_fronted.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthRemoteDataSource
): AuthRepository {
    override suspend fun sendEmail(sendEmailRequestEntity: SendEmailRequestEntity): SendEmailResponseEntity {
        return runCatching {
            authDataSource.sendEmail(sendEmailRequestEntity.toSendEmailRequestDto())
                .result.toSendEmailResponseEntity()
        }.getOrElse { err -> throw  err }
    }

}