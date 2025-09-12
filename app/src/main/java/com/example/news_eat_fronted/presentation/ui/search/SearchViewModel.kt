package com.example.news_eat_fronted.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.news.GetSearchedNewsRequestEntity
import com.example.news_eat_fronted.domain.entity.response.news.CategoryNewsResponseEntity
import com.example.news_eat_fronted.domain.entity.response.news.GetSearchedNewsResponseEntity
import com.example.news_eat_fronted.domain.usecase.news.GetSearchedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
): ViewModel() {

    private val _keyword = MutableStateFlow<String>("")
    val keyword: StateFlow<String> = _keyword

    private val _getSearchedNewsState = MutableStateFlow<GetSearchedNewsResponseEntity?>(null)
    val getSearchedNewsState: StateFlow<GetSearchedNewsResponseEntity?> = _getSearchedNewsState

    private val _newsList = MutableStateFlow<List<CategoryNewsResponseEntity>>(emptyList())
    val newsList: MutableStateFlow<List<CategoryNewsResponseEntity>> = _newsList

    private var lastNewsId: Long = 0
    private var hasNext: Boolean = true
    private var isLoading: Boolean = false

    fun getSearchedNews(isLoadMore: Boolean = false) {
        if(isLoading || !hasNext) return
        isLoading = true

        viewModelScope.launch {
            try {
                val searchedNewsResponseEntity = getSearchedNewsUseCase(
                    getSearchedNewsRequestEntity = GetSearchedNewsRequestEntity(
                        keyword = _keyword.value,
                        lastNewsId = if(isLoadMore) lastNewsId else 0,
                        size = 10
                    )
                )
                _getSearchedNewsState.value = searchedNewsResponseEntity

                val newsList = if(isLoadMore) {
                    _newsList.value + searchedNewsResponseEntity.searchNewsResponses
                } else {
                    searchedNewsResponseEntity.searchNewsResponses
                }
                _newsList.value = newsList

                lastNewsId = newsList.lastOrNull()?.newsId ?: 0
                hasNext = searchedNewsResponseEntity.hasNext
            } catch (ex: Exception) {
            } finally {
                isLoading = false
            }
        }
    }

    fun onChangeKeyword(keyword: String) {
        _keyword.value = keyword
    }

    fun resetNewsList() {
        lastNewsId = 0
        hasNext = true
        _newsList.value = emptyList()
    }
}