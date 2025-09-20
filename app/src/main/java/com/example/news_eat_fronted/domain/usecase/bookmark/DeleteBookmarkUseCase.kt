package com.example.news_eat_fronted.domain.usecase.bookmark

import com.example.news_eat_fronted.domain.repository.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(bookmarkId: Long)
    = bookmarkRepository.deleteBookmark(bookmarkId)
}