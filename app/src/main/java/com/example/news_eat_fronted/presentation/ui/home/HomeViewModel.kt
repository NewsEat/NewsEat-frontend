package com.example.news_eat_fronted.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.domain.entity.request.user.SetDetoxModeRequestEntity
import com.example.news_eat_fronted.domain.entity.response.home.GetHomeNewsSectionResponseEntity
import com.example.news_eat_fronted.domain.entity.response.home.GetLatestNewsResponseEntity
import com.example.news_eat_fronted.domain.entity.response.home.NewsItemEntity
import com.example.news_eat_fronted.domain.entity.response.user.SetDetoxModeResponseEntity
import com.example.news_eat_fronted.domain.usecase.home.GetHomeNewsSectionsUseCase
import com.example.news_eat_fronted.domain.usecase.home.GetLatestNewsUseCase
import com.example.news_eat_fronted.domain.usecase.user.SetDetoxModeUseCase
import com.example.news_eat_fronted.presentation.model.CategoryItem
import com.example.news_eat_fronted.presentation.model.HomeNewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val setDetoxModeUseCase: SetDetoxModeUseCase,
    private val getHomeNewsSectionsUseCase: GetHomeNewsSectionsUseCase,
    private val getLatestNewsUseCase: GetLatestNewsUseCase
): ViewModel() {
    private val _nickname = MutableStateFlow("쫀득물만두")
    val nickname: StateFlow<String> = _nickname

    private val _interests = MutableStateFlow<List<String>>(listOf())
    val interests: StateFlow<List<String>> = _interests

    val categoryListMap = mapOf(
        "정치" to CategoryItem(1, R.drawable.category_politics_unselected, R.string.category_politics),
        "경제" to CategoryItem(2, R.drawable.category_economy_unselected, R.string.category_economy),
        "사회" to CategoryItem(3, R.drawable.category_social_unselected, R.string.category_social),
        "생활/문화" to CategoryItem(4, R.drawable.category_culture_unselected, R.string.category_culture),
        "IT/과학" to CategoryItem(5, R.drawable.category_science_unselected, R.string.category_science),
        "연예" to CategoryItem(6, R.drawable.category_entertainment_unselected, R.string.category_entertainment),
        "스포츠" to CategoryItem(7, R.drawable.category_sports_unselected, R.string.category_sports),
        "세계" to CategoryItem(8, R.drawable.category_world_unselected, R.string.category_world),
    )

    private val _recommendList = MutableStateFlow<List<NewsItemEntity>>(emptyList())
    val recommendList: StateFlow<List<NewsItemEntity>> = _recommendList


    private val _homeNewsSectionsState = MutableStateFlow<GetHomeNewsSectionResponseEntity?>(null)
    val homeNewsSectionsState: StateFlow<GetHomeNewsSectionResponseEntity?> = _homeNewsSectionsState

    private val _latestNewsState = MutableStateFlow<GetLatestNewsResponseEntity?>(null)
    val latestNewsState: StateFlow<GetLatestNewsResponseEntity?> = _latestNewsState

    fun setDetoxMode(isChecked: Boolean) {
        viewModelScope.launch {
            try {
                val setDetoxModeResponseEntity = setDetoxModeUseCase(
                    setDetoxModeRequestEntity = SetDetoxModeRequestEntity(
                        isDetoxMode = isChecked
                    )
                )
            } catch (ex: Exception) {}
        }
    }

    fun getHomeNewsSections() {
        viewModelScope.launch {
            try {
                val getHomeNewsSectionsResponseEntity = getHomeNewsSectionsUseCase()
                _homeNewsSectionsState.value = getHomeNewsSectionsResponseEntity
            } catch (ex: Exception) {}
        }
    }

    fun getLatestNews() {
        viewModelScope.launch {
            try {
                val getLatestNewsResponseEntity = getLatestNewsUseCase()
                _latestNewsState.value = getLatestNewsResponseEntity
            } catch (ex: Exception) {}
        }
    }

    fun setInterest(interest: List<String>) {
        _interests.value = interest
    }
}