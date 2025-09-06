package com.example.news_eat_fronted.data.repositoryImpl

import com.example.news_eat_fronted.data.datasource.UserRemoteDataSource
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
}