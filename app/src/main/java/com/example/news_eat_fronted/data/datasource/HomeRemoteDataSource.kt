package com.example.news_eat_fronted.data.datasource

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.response.home.GetHomeNewsSectionsResponseDto

interface HomeRemoteDataSource {
    suspend fun getHomeNewsSections(): BaseResponse<GetHomeNewsSectionsResponseDto>
}