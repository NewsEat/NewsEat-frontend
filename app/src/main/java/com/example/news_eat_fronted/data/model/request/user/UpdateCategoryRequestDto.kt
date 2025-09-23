package com.example.news_eat_fronted.data.model.request.user

import com.google.gson.annotations.SerializedName

data class UpdateCategoryRequestDto (
    @SerializedName("categoryIds")
    val categoryIds: List<Int>
)