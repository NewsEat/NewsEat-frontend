package com.example.news_eat_fronted.data.service

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.response.home.GetHomeNewsSectionsResponseDto
import retrofit2.http.GET

interface HomeService {
    @GET("home/news-sections")
    suspend fun getHomeNewsSections(): BaseResponse<GetHomeNewsSectionsResponseDto>
}