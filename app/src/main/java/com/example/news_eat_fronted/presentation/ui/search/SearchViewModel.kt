package com.example.news_eat_fronted.presentation.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.news.GetSearchedNewsRequestEntity
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
                Log.d("okhttp-viewModel", searchedNewsResponseEntity.toString())
                _getSearchedNewsState.value = searchedNewsResponseEntity
            } catch (ex: Exception) {}
        }
    }

    fun onChangeKeyword(keyword: String) {
        _keyword.value = keyword
    }

}