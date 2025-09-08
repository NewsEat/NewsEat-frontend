package com.example.news_eat_fronted.data.datasource

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.GetCategoryNewsResponseDto
import com.example.news_eat_fronted.data.model.request.news.GetCategoryNewsRequestDto

interface NewsRemoteDataSource {
    suspend fun getCategoryNews(getCategoryNewsRequestDto: GetCategoryNewsRequestDto): BaseResponse<GetCategoryNewsResponseDto>
}