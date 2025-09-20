package com.example.news_eat_fronted.domain.usecase.news

import com.example.news_eat_fronted.domain.entity.response.news.GetRecommendationsResponseEntity
import com.example.news_eat_fronted.domain.repository.NewsRepository
import javax.inject.Inject

class GetRecommendationsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(newsId: Long): GetRecommendationsResponseEntity
    = newsRepository.getRecommendations(newsId)
}