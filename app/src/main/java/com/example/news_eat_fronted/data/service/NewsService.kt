package com.example.news_eat_fronted.data.service

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.GetCategoryNewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("news")
    suspend fun getCategoryNews(
        @Query("category") category: String,
        @Query("lastNewsId") lastNewsId: Long,
        @Query("size") size: Int
    ): BaseResponse<GetCategoryNewsResponseDto>
}