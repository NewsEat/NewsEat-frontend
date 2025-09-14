package com.example.news_eat_fronted.data.datasourceImpl

import com.example.news_eat_fronted.data.datasource.NewsRemoteDataSource
import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.response.news.GetCategoryNewsResponseDto
import com.example.news_eat_fronted.data.model.request.news.GetCategoryNewsRequestDto
import com.example.news_eat_fronted.data.model.request.news.GetSearchedNewsRequestDto
import com.example.news_eat_fronted.data.model.response.news.GetNewsDetailResponseDto
import com.example.news_eat_fronted.data.model.response.news.GetRecommendationsResponseDto
import com.example.news_eat_fronted.data.model.response.news.GetSearchedNewsResponseDto
import com.example.news_eat_fronted.data.model.response.news.NewsSummaryResponseDto
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

    override suspend fun getNewsSummary(newsId: Long): BaseResponse<NewsSummaryResponseDto>
    = newsService.getNewsSummary(
        newsId = newsId
    )

    override suspend fun getSearchedNews(getSearchedNewsRequestDto: GetSearchedNewsRequestDto): BaseResponse<GetSearchedNewsResponseDto>
    = newsService.getSearchedNews(
        keyword = getSearchedNewsRequestDto.keyword,
        lastNewsId = getSearchedNewsRequestDto.lastNewsId,
        size = getSearchedNewsRequestDto.size
    )

    override suspend fun getRecommendations(newsId: Long): BaseResponse<GetRecommendationsResponseDto>
    = newsService.getRecommendations(newsId)
}