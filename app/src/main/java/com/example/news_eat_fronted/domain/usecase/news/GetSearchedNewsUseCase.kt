package com.example.news_eat_fronted.domain.usecase.news

import com.example.news_eat_fronted.domain.entity.request.news.GetSearchedNewsRequestEntity
import com.example.news_eat_fronted.domain.entity.response.news.GetCategoryNewsResponseEntity
import com.example.news_eat_fronted.domain.repository.NewsRepository
import javax.inject.Inject

class GetSearchedNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(getSearchedNewsRequestEntity: GetSearchedNewsRequestEntity): GetCategoryNewsResponseEntity
    = newsRepository.getSearchedNews(getSearchedNewsRequestEntity)
}