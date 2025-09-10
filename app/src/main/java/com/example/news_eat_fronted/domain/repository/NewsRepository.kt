package com.example.news_eat_fronted.domain.repository

import com.example.news_eat_fronted.domain.entity.request.news.GetCategoryNewsRequestEntity
import com.example.news_eat_fronted.domain.entity.request.news.GetNewsDetailResponseEntity
import com.example.news_eat_fronted.domain.entity.request.news.GetSearchedNewsRequestEntity
import com.example.news_eat_fronted.domain.entity.request.news.NewsSummaryResponseEntity
import com.example.news_eat_fronted.domain.entity.response.news.GetCategoryNewsResponseEntity

interface NewsRepository {
    suspend fun getCategoryNews(getCategoryNewsRequestEntity: GetCategoryNewsRequestEntity): GetCategoryNewsResponseEntity

    suspend fun getNewsDetail(newsId: Long): GetNewsDetailResponseEntity

    suspend fun getNewsSummary(newsId: Long): NewsSummaryResponseEntity

    suspend fun getSearchedNews(getSearchedNewsRequestEntity: GetSearchedNewsRequestEntity): GetCategoryNewsResponseEntity
}