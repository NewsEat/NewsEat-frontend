package com.example.news_eat_fronted.domain.usecase.news

import com.example.news_eat_fronted.domain.entity.request.news.NewsSummaryResponseEntity
import com.example.news_eat_fronted.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsSummaryUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(newsId: Long): NewsSummaryResponseEntity
    = newsRepository.getNewsSummary(newsId)
}