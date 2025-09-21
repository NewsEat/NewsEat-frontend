package com.example.news_eat_fronted.domain.usecase.home

import com.example.news_eat_fronted.domain.entity.response.home.GetHomeNewsSectionResponseEntity
import com.example.news_eat_fronted.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeNewsSectionsUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke()
    = homeRepository.getHomeNewsSections()
}