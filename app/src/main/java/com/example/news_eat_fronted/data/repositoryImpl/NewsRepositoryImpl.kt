package com.example.news_eat_fronted.data.repositoryImpl

import com.example.news_eat_fronted.data.datasource.NewsRemoteDataSource
import com.example.news_eat_fronted.domain.entity.request.news.GetCategoryNewsRequestEntity
import com.example.news_eat_fronted.domain.entity.request.news.GetNewsDetailResponseEntity
import com.example.news_eat_fronted.domain.entity.request.news.GetSearchedNewsRequestEntity
import com.example.news_eat_fronted.domain.entity.request.news.NewsSummaryResponseEntity
import com.example.news_eat_fronted.domain.entity.response.news.GetCategoryNewsResponseEntity
import com.example.news_eat_fronted.domain.entity.response.news.GetSearchedNewsResponseEntity
import com.example.news_eat_fronted.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun getCategoryNews(getCategoryNewsRequestEntity: GetCategoryNewsRequestEntity): GetCategoryNewsResponseEntity {
        return runCatching {
            newsDataSource.getCategoryNews(getCategoryNewsRequestEntity.toGetCategoryNewsRequestDto())
                .result.toGetCategoryNewsResponseEntity()
        }.getOrElse { err -> throw  err }
    }

    override suspend fun getNewsDetail(newsId: Long): GetNewsDetailResponseEntity {
        return runCatching {
            newsDataSource.getNewsDetail(newsId)
                .result.toGetNewsDetailResponseEntity()
        }.getOrElse { err -> throw err }
    }

    override suspend fun getNewsSummary(newsId: Long): NewsSummaryResponseEntity {
        return runCatching {
            newsDataSource.getNewsSummary(newsId)
                .result.toNewsSummaryResponseEntity()
        }.getOrElse { err -> throw err }
    }

    override suspend fun getSearchedNews(getSearchedNewsRequestEntity: GetSearchedNewsRequestEntity): GetSearchedNewsResponseEntity {
        return runCatching {
            newsDataSource.getSearchedNews(getSearchedNewsRequestEntity.toGetSearchedNewsRequestDto())
                .result.toGetSearchedNewsResponseEntity()
        }.getOrElse { err -> throw err }
    }
}