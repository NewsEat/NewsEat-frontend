package com.example.news_eat_fronted.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.news.GetSearchedNewsRequestEntity
import com.example.news_eat_fronted.domain.entity.response.news.GetCategoryNewsResponseEntity
import com.example.news_eat_fronted.domain.usecase.news.GetSearchedNewsUseCase
import com.example.news_eat_fronted.presentation.model.HomeNewsItem
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

    private val _getSearchedNewsState = MutableStateFlow<GetCategoryNewsResponseEntity?>(null)
    val getSearchedNewsState: StateFlow<GetCategoryNewsResponseEntity?> = _getSearchedNewsState

    fun getSearchedNews() {
        viewModelScope.launch {
            try {
                val searchedNewsResponseEntity = getSearchedNewsUseCase(
                    getSearchedNewsRequestEntity = GetSearchedNewsRequestEntity(
                        keyword = _keyword.value,
                        lastNewsId = 0,
                        size = 10
                    )
                )
                _getSearchedNewsState.value = searchedNewsResponseEntity
            } catch (ex: Exception) {}
        }
    }

    fun onChangeKeyword(keyword: String) {
        _keyword.value = keyword
    }

}