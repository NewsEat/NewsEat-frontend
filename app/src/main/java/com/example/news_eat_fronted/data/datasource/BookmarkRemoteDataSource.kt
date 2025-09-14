package com.example.news_eat_fronted.data.datasource

import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.bookmark.GetBookmarkListRequestDto
import com.example.news_eat_fronted.data.model.request.news.GetCategoryNewsRequestDto
import com.example.news_eat_fronted.data.model.response.bookmark.BookmarkIdResponseDto
import com.example.news_eat_fronted.data.model.response.bookmark.GetBookmarkListResponseDto
import com.example.news_eat_fronted.data.model.response.bookmark.GetBookmarkedNewsDetailResponseDto
import com.example.news_eat_fronted.data.model.response.news.GetCategoryNewsResponseDto

interface BookmarkRemoteDataSource {
    suspend fun postBookmark(newsId: Long): BaseResponse<BookmarkIdResponseDto>

    suspend fun deleteBookmark(bookmarkId: Long): BaseResponse<Unit>

    suspend fun getBookmarkList(getBookmarkListRequestDto: GetBookmarkListRequestDto): BaseResponse<GetBookmarkListResponseDto>

    suspend fun getBookmarkedNewsDetail(bookmarkId: Long): BaseResponse<GetBookmarkedNewsDetailResponseDto>
}