package com.example.news_eat_fronted.data.model.request.news

import com.google.gson.annotations.SerializedName

data class GetCategoryNewsRequestDto(
    @SerializedName("category")
    val category: String,
    @SerializedName("lastNewsId")
    val lastNewsId: Long,
    @SerializedName("size")
    val size: Int = 10
)