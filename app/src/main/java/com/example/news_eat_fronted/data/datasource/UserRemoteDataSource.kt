package com.example.news_eat_fronted.data.datasource

import com.example.news_eat_fronted.data.model.BaseResponse

interface UserRemoteDataSource {
    suspend fun withdraw(): BaseResponse<Unit>
}