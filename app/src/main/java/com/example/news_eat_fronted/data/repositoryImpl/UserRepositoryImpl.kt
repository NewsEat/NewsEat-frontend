package com.example.news_eat_fronted.data.repositoryImpl

import com.example.news_eat_fronted.data.datasource.UserRemoteDataSource
import com.example.news_eat_fronted.domain.entity.request.user.SetDetoxModeRequestEntity
import com.example.news_eat_fronted.domain.entity.response.user.GetNicknameResponseEntity
import com.example.news_eat_fronted.domain.entity.response.user.SetDetoxModeResponseEntity
import com.example.news_eat_fronted.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserRemoteDataSource
): UserRepository {
    override suspend fun withdraw() {
        return runCatching {
            userDataSource.withdraw().result
        }.getOrElse { err -> throw err }
    }

    override suspend fun setDetoxMode(setDetoxModeRequestEntity: SetDetoxModeRequestEntity): SetDetoxModeResponseEntity {
        return runCatching {
            userDataSource.setDetoxMode(setDetoxModeRequestEntity.toSetDetoxModeRequestDto())
                .result.toSetDetoxModeResponseEntity()
        }.getOrElse { err -> throw err }
    }

    override suspend fun getNickname(): GetNicknameResponseEntity {
        return runCatching {
            userDataSource.getNickname()
                .result.toGetNicknameResponseEntity()
        }.getOrElse { err -> throw err }
    }
}