package com.example.news_eat_fronted.data.service

import com.example.news_eat_fronted.data.model.BaseResponse
import retrofit2.http.DELETE

interface UserService {
    @DELETE("auth/withdraw")
    suspend fun withdraw(): BaseResponse<Unit>
}