package com.example.news_eat_fronted.data.datasourceImpl

import com.example.news_eat_fronted.data.datasource.NewsRemoteDataSource
import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.response.news.GetCategoryNewsResponseDto
import com.example.news_eat_fronted.data.model.request.news.GetCategoryNewsRequestDto
import com.example.news_eat_fronted.data.model.response.news.GetNewsDetailResponseDto
import com.example.news_eat_fronted.data.service.NewsService
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsService: NewsService
): NewsRemoteDataSource {
    override suspend fun getCategoryNews(getCategoryNewsRequestDto: GetCategoryNewsRequestDto): BaseResponse<GetCategoryNewsResponseDto>
    = newsService.getCategoryNews(
        category = getCategoryNewsRequestDto.category,
        lastNewsId = getCategoryNewsRequestDto.lastNewsId,
        size = getCategoryNewsRequestDto.size
        )

    override suspend fun getNewsDetail(newsId: Long): BaseResponse<GetNewsDetailResponseDto>
    = newsService.getNewsDetail(
        newsId = newsId
    )
}