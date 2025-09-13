package com.example.news_eat_fronted.domain.repository

import com.example.news_eat_fronted.domain.entity.response.bookmark.BookmarkIdResponseEntity

interface BookmarkRepository {
    suspend fun postBookmark(newsId: Long): BookmarkIdResponseEntity

    suspend fun deleteBookmark(bookmarkId: Long)
}