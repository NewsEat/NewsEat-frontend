package com.example.news_eat_fronted.data.model.response.home

import com.example.news_eat_fronted.domain.entity.response.home.GetHomeNewsSectionResponseEntity
import com.example.news_eat_fronted.domain.entity.response.home.NewsItemEntity
import com.example.news_eat_fronted.domain.entity.response.home.SectionEntity
import com.google.gson.annotations.SerializedName

data class GetHomeNewsSectionsResponseDto(
    @SerializedName("isDetox")
    val isDetox: Boolean,
    @SerializedName("sections")
    val sections: List<SectionDto>
) {
    data class SectionDto(
        @SerializedName("type")
        val type: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("newsList")
        val newsList: List<NewsItemDto>
    )

    data class NewsItemDto(
        @SerializedName("newsId")
        val newsId: Long,
        @SerializedName("imgUrl")
        val imgUrl: String,
        @SerializedName("title")
        val title: String
    )

    fun toGetHomeNewsSectionResponseEntity() = GetHomeNewsSectionResponseEntity(
        isDetox = isDetox,
        sections = sections.map { sectionDto ->
            SectionEntity(
                type = sectionDto.type,
                title = sectionDto.title,
                newsList = sectionDto.newsList.map { newsDto ->
                    NewsItemEntity(
                        newsId = newsDto.newsId,
                        imgUrl = newsDto.imgUrl,
                        title = newsDto.title
                    )
                }
            )
        }
    )
}
