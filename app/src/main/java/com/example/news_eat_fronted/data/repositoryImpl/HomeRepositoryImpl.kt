package com.example.news_eat_fronted.data.repositoryImpl

import com.example.news_eat_fronted.data.datasource.HomeRemoteDataSource
import com.example.news_eat_fronted.domain.entity.response.home.GetHomeNewsSectionResponseEntity
import com.example.news_eat_fronted.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource
): HomeRepository {
    override suspend fun getHomeNewsSections(): GetHomeNewsSectionResponseEntity {
        return runCatching {
            homeRemoteDataSource.getHomeNewsSections()
                .result.toGetHomeNewsSectionResponseEntity()
        }.getOrElse { err -> throw  err }
    }
}