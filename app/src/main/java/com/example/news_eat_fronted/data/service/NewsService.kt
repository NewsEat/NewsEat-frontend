package com.example.news_eat_fronted.data.service

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.response.news.GetCategoryNewsResponseDto
import com.example.news_eat_fronted.data.model.response.news.GetNewsDetailResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {
    @GET("news")
    suspend fun getCategoryNews(
        @Query("category") category: String,
        @Query("lastNewsId") lastNewsId: Long,
        @Query("size") size: Int
    ): BaseResponse<GetCategoryNewsResponseDto>

    @GET("news/{newsId}")
    suspend fun getNewsDetail(
        @Path("newsId") newsId: Long
    ): BaseResponse<GetNewsDetailResponseDto>
}