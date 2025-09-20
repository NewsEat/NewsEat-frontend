package com.example.news_eat_fronted.domain.repository

import com.example.news_eat_fronted.domain.entity.response.home.GetHomeNewsSectionResponseEntity
import com.example.news_eat_fronted.domain.entity.response.home.GetLatestNewsResponseEntity

interface HomeRepository {
    suspend fun getHomeNewsSections(): GetHomeNewsSectionResponseEntity

    suspend fun getLatestNews(): GetLatestNewsResponseEntity
}