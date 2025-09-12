package com.example.news_eat_fronted.data.model.response.news

import com.example.news_eat_fronted.domain.entity.request.news.NewsSummaryResponseEntity
import com.google.gson.annotations.SerializedName

data class NewsSummaryResponseDto (
    @SerializedName("title")
    val title: String,
    @SerializedName("sentiment")
    val sentiment: String,
    @SerializedName("summaryResult")
    val summaryResult: String
) {
    fun toNewsSummaryResponseEntity() = NewsSummaryResponseEntity(
        title = title,
        sentiment = sentiment,
        summaryResult = summaryResult
    )
}