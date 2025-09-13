package com.example.news_eat_fronted.data.repositoryImpl

import com.example.news_eat_fronted.data.datasource.BookmarkRemoteDataSource
import com.example.news_eat_fronted.domain.entity.response.bookmark.BookmarkIdResponseEntity
import com.example.news_eat_fronted.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkRemoteDataSource: BookmarkRemoteDataSource
): BookmarkRepository {
    override suspend fun postBookmark(newsId: Long): BookmarkIdResponseEntity {
        return runCatching {
            bookmarkRemoteDataSource.postBookmark(newsId)
                .result.toBookmarkResponseEntity()
        }.getOrElse { err -> throw  err }
    }

    override suspend fun deleteBookmark(bookmarkId: Long) {
        return runCatching {
            bookmarkRemoteDataSource.deleteBookmark(bookmarkId)
                .result
        }.getOrElse { err -> throw err }
    }
}