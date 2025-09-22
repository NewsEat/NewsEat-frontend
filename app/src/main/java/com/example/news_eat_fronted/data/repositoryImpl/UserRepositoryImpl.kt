package com.example.news_eat_fronted.data.repositoryImpl

import com.example.news_eat_fronted.data.datasource.UserRemoteDataSource
import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.domain.entity.request.user.SetDetoxModeRequestEntity
import com.example.news_eat_fronted.domain.entity.request.user.UpdateNicknameRequestEntity
import com.example.news_eat_fronted.domain.entity.response.user.GetMyPageProfileResponseEntity
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

    override suspend fun getMyPageProfile(): GetMyPageProfileResponseEntity {
        return runCatching {
            userDataSource.getMyPageProfile()
                .result.toGetMyPageProfileResponseEntity()
        }.getOrElse { err -> throw err }
    }

    override suspend fun updateNickname(updateNicknameRequestEntity: UpdateNicknameRequestEntity): UpdateNicknameRequestEntity {
        return runCatching {
            userDataSource.updateNickname(updateNicknameRequestEntity.toUpdateNicknameRequestDto())
            updateNicknameRequestEntity
        }.getOrElse { err -> throw err }
    }
}