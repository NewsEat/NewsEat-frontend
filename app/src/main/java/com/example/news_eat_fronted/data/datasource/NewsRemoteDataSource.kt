package com.example.news_eat_fronted.data.datasource

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.response.news.GetCategoryNewsResponseDto
import com.example.news_eat_fronted.data.model.request.news.GetCategoryNewsRequestDto
import com.example.news_eat_fronted.data.model.response.news.GetNewsDetailResponseDto

interface NewsRemoteDataSource {
    suspend fun getCategoryNews(getCategoryNewsRequestDto: GetCategoryNewsRequestDto): BaseResponse<GetCategoryNewsResponseDto>

    suspend fun getNewsDetail(newsId: Long): BaseResponse<GetNewsDetailResponseDto>
}