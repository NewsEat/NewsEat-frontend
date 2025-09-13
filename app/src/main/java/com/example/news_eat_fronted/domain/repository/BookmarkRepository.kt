package com.example.news_eat_fronted.domain.repository

import com.example.news_eat_fronted.domain.entity.request.bookmark.GetBookmarkListRequestEntity
import com.example.news_eat_fronted.domain.entity.response.bookmark.BookmarkIdResponseEntity
import com.example.news_eat_fronted.domain.entity.response.bookmark.GetBookmarkListResponseEntity

interface BookmarkRepository {
    suspend fun postBookmark(newsId: Long): BookmarkIdResponseEntity

    suspend fun deleteBookmark(bookmarkId: Long)

    suspend fun getBookmarkList(getBookmarkListRequestEntity: GetBookmarkListRequestEntity): GetBookmarkListResponseEntity
}