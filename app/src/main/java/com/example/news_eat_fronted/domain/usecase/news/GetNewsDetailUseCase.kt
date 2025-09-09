package com.example.news_eat_fronted.domain.usecase.news

import com.example.news_eat_fronted.domain.entity.request.news.GetNewsDetailResponseEntity
import com.example.news_eat_fronted.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsDetailUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(newsId: Long): GetNewsDetailResponseEntity
    = newsRepository.getNewsDetail(newsId)
}