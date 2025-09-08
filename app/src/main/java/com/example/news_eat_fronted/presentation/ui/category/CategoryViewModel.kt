package com.example.news_eat_fronted.presentation.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.news.GetCategoryNewsRequestEntity
import com.example.news_eat_fronted.domain.entity.response.news.GetCategoryNewsResponseEntity
import com.example.news_eat_fronted.domain.usecase.news.GetCategoryNewsUseCase
import com.example.news_eat_fronted.presentation.model.HomeNewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoryNewsUseCase: GetCategoryNewsUseCase
): ViewModel() {
    val categoryList = arrayListOf("정치", "경제", "사회", "생활/문화", "IT/과학", "연예", "스포츠", "세계")

    private val _selectedPosition = MutableStateFlow<Int>(0)
    val selectedPosition: StateFlow<Int> = _selectedPosition

    private val _getCategoryNewsState = MutableStateFlow<GetCategoryNewsResponseEntity?>(null)
    val getCategoryNewsState: StateFlow<GetCategoryNewsResponseEntity?> = _getCategoryNewsState

    fun getCategoryNews() {
        viewModelScope.launch {
            try {
                val getCategoryNewsResponseEntity = getCategoryNewsUseCase(
                    getCategoryNewsRequestEntity = GetCategoryNewsRequestEntity(
                        category = (_selectedPosition.value + 1).toString().padStart(3, '0'),
                        lastNewsId = 0,
                        size = 10
                    )
                )
                _getCategoryNewsState.value = getCategoryNewsResponseEntity
            } catch (ex: Exception) {
            }
        }
    }

    fun updateSelectedPosition(position: Int) {
        _selectedPosition.value = position
    }
}