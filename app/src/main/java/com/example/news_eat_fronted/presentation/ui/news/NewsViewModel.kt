package com.example.news_eat_fronted.presentation.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.news.GetNewsDetailResponseEntity
import com.example.news_eat_fronted.domain.entity.request.news.NewsSummaryResponseEntity
import com.example.news_eat_fronted.domain.usecase.news.GetNewsDetailUseCase
import com.example.news_eat_fronted.domain.usecase.news.GetNewsSummaryUseCase
import com.example.news_eat_fronted.presentation.model.NewsDetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsDetailUseCase: GetNewsDetailUseCase,
    private val getNewsSummaryUseCase: GetNewsSummaryUseCase
) : ViewModel() {

    private val _newsId = MutableStateFlow<Long>(-1L)
    val newsId: StateFlow<Long> = _newsId

    private val _newsDetailState = MutableStateFlow<GetNewsDetailResponseEntity?>(null)
    val newsDetailState: StateFlow<GetNewsDetailResponseEntity?> = _newsDetailState

    private val _newsSummaryState = MutableStateFlow<NewsSummaryResponseEntity?>(null)
    val newsSummaryState: StateFlow<NewsSummaryResponseEntity?> = _newsSummaryState

    fun getNewsDetail() {
        viewModelScope.launch {
            try {
                val getNewsDetailResponseEntity = getNewsDetailUseCase(
                    newsId = _newsId.value
                )
                _newsDetailState.value = getNewsDetailResponseEntity
            } catch (ex: Exception) {}
        }
    }

    fun getNewsSummary() {
        viewModelScope.launch {
            try {
                val newsSummaryResponseEntity = getNewsSummaryUseCase(
                    newsId = _newsId.value
                )
                _newsSummaryState.value = newsSummaryResponseEntity
            } catch (ex: Exception) {}
        }
    }

    fun setNewsId(id: Long) {
        _newsId.value = id
    }
}
