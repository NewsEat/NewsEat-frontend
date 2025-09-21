package com.example.news_eat_fronted.data.datasourceImpl

import com.example.news_eat_fronted.data.datasource.BookmarkRemoteDataSource
import com.example.news_eat_fronted.data.model.BaseResponse
import com.example.news_eat_fronted.data.model.request.bookmark.GetBookmarkListRequestDto
import com.example.news_eat_fronted.data.model.response.bookmark.BookmarkIdResponseDto
import com.example.news_eat_fronted.data.model.response.bookmark.GetBookmarkListResponseDto
import com.example.news_eat_fronted.data.model.response.bookmark.GetBookmarkedNewsDetailResponseDto
import com.example.news_eat_fronted.data.model.response.news.NewsSummaryResponseDto
import com.example.news_eat_fronted.data.service.BookmarkService
import javax.inject.Inject

class BookmarkRemoteDataSourceImpl @Inject constructor(
    private val bookmarkService: BookmarkService
): BookmarkRemoteDataSource {
    override suspend fun postBookmark(newsId: Long): BaseResponse<BookmarkIdResponseDto>
    = bookmarkService.postBookmark(newsId)

    override suspend fun deleteBookmark(bookmarkId: Long): BaseResponse<Unit>
    = bookmarkService.deleteBookmark(bookmarkId)

    override suspend fun getBookmarkList(getBookmarkListRequestDto: GetBookmarkListRequestDto): BaseResponse<GetBookmarkListResponseDto>
    = bookmarkService.getBookmarkList(
        lastBookmarkId = getBookmarkListRequestDto.lastBookmarkId,
        size = getBookmarkListRequestDto.size
    )

    override suspend fun getBookmarkedNewsDetail(bookmarkId: Long): BaseResponse<GetBookmarkedNewsDetailResponseDto>
    = bookmarkService.getBookmarkedNewsDetail(bookmarkId)

    override suspend fun getBookmarkedNewsSummary(bookmarkId: Long): BaseResponse<NewsSummaryResponseDto>
    = bookmarkService.getBookmarkedNewsSummary(bookmarkId)
}