package com.example.news_eat_fronted.data.model.request.bookmark

import com.google.gson.annotations.SerializedName

data class GetBookmarkListRequestDto (
    @SerializedName("lastBookmarkId")
    val lastBookmarkId: Long,
    @SerializedName("size")
    val size: Int
)