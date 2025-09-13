package com.example.news_eat_fronted.domain.usecase.bookmark

import com.example.news_eat_fronted.domain.entity.request.bookmark.GetBookmarkListRequestEntity
import com.example.news_eat_fronted.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetBookmarkListUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(getBookmarkListRequestEntity: GetBookmarkListRequestEntity)
    = bookmarkRepository.getBookmarkList(getBookmarkListRequestEntity)
}