package com.example.news_eat_fronted.data.service

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.response.bookmark.BookmarkIdResponseDto
import com.example.news_eat_fronted.data.model.response.bookmark.GetBookmarkListResponseDto
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BookmarkService {
    @POST("bookmarks/{newsId}")
    suspend fun postBookmark(
        @Path("newsId") newsId: Long
    ): BaseResponse<BookmarkIdResponseDto>

    @DELETE("bookmarks/{bookmarkId}")
    suspend fun deleteBookmark(
        @Path("bookmarkId") bookmarkId: Long
    ): BaseResponse<Unit>

    @GET("bookmarks")
    suspend fun getBookmarkList(
        @Query("lastBookmarkId") lastBookmarkId: Long,
        @Query("size") size: Int
    ): BaseResponse<GetBookmarkListResponseDto>
}