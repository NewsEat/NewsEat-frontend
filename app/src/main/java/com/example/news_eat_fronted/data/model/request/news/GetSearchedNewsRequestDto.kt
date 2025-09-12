package com.example.news_eat_fronted.data.model.request.news

import com.google.gson.annotations.SerializedName

data class GetSearchedNewsRequestDto(
    @SerializedName("keyword ")
    val keyword: String,
    @SerializedName("lastNewsId")
    val lastNewsId: Long,
    @SerializedName("size")
    val size: Int
)