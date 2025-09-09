package com.example.news_eat_fronted.data.datasource

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.response.news.GetCategoryNewsResponseDto
import com.example.news_eat_fronted.data.model.request.news.GetCategoryNewsRequestDto
import com.example.news_eat_fronted.data.model.response.news.GetNewsDetailResponseDto
import com.example.news_eat_fronted.data.model.response.news.NewsSummaryResponseDto

interface NewsRemoteDataSource {
    suspend fun getCategoryNews(getCategoryNewsRequestDto: GetCategoryNewsRequestDto): BaseResponse<GetCategoryNewsResponseDto>

    suspend fun getNewsDetail(newsId: Long): BaseResponse<GetNewsDetailResponseDto>

    suspend fun getNewsSummary(newsId: Long): BaseResponse<NewsSummaryResponseDto>
}