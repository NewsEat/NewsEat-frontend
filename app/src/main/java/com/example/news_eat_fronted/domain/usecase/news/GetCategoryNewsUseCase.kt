package com.example.news_eat_fronted.domain.usecase.news

import com.example.news_eat_fronted.domain.entity.request.news.GetCategoryNewsRequestEntity
import com.example.news_eat_fronted.domain.entity.response.news.GetCategoryNewsResponseEntity
import com.example.news_eat_fronted.domain.repository.NewsRepository
import javax.inject.Inject

class GetCategoryNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(getCategoryNewsRequestEntity: GetCategoryNewsRequestEntity): GetCategoryNewsResponseEntity
    = newsRepository.getCategoryNews(getCategoryNewsRequestEntity)
}