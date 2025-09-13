package com.example.news_eat_fronted.domain.usecase.bookmark

import com.example.news_eat_fronted.domain.entity.response.bookmark.BookmarkIdResponseEntity
import com.example.news_eat_fronted.domain.repository.BookmarkRepository
import javax.inject.Inject

class PostBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(newsId: Long): BookmarkIdResponseEntity
    = bookmarkRepository.postBookmark(newsId)
}