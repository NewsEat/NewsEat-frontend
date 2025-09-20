package com.example.news_eat_fronted.data.datasourceImpl

import com.example.news_eat_fronted.data.datasource.HomeRemoteDataSource
import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.response.home.GetHomeNewsSectionsResponseDto
import com.example.news_eat_fronted.data.service.HomeService
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeService: HomeService
): HomeRemoteDataSource {
    override suspend fun getHomeNewsSections(): BaseResponse<GetHomeNewsSectionsResponseDto>
    = homeService.getHomeNewsSections()
}