package com.example.news_eat_fronted.domain.entity.request.bookmark

import com.example.news_eat_fronted.data.model.request.bookmark.GetBookmarkListRequestDto

data class GetBookmarkListRequestEntity(
    val lastBookmarkId: Long,
    val size: Int
) {
    fun toGetBookmarkListRequestDto() = GetBookmarkListRequestDto(
        lastBookmarkId = lastBookmarkId,
        size = size
    )
}
