package com.example.news_eat_fronted.data.service

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.response.bookmark.BookmarkIdResponseDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookmarkService {
    @POST("bookmarks/{newsId}")
    suspend fun postBookmark(
        @Path("newsId") newsId: Long
    ): BaseResponse<BookmarkIdResponseDto>

    @DELETE("bookmarks/{bookmarkId}")
    suspend fun deleteBookmark(
        @Path("bookmarkId") bookmarkId: Long
    ): BaseResponse<Unit>
}