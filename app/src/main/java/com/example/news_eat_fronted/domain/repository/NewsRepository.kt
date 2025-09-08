package com.example.news_eat_fronted.domain.repository

import com.example.news_eat_fronted.domain.entity.request.news.GetCategoryNewsRequestEntity
import com.example.news_eat_fronted.domain.entity.response.news.GetCategoryNewsResponseEntity

interface NewsRepository {
    suspend fun getCategoryNews(getCategoryNewsRequestEntity: GetCategoryNewsRequestEntity): GetCategoryNewsResponseEntity
}