package com.example.news_eat_fronted.presentation.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.news.GetNewsDetailResponseEntity
import com.example.news_eat_fronted.domain.entity.request.news.NewsSummaryResponseEntity
import com.example.news_eat_fronted.domain.entity.response.bookmark.BookmarkIdResponseEntity
import com.example.news_eat_fronted.domain.usecase.bookmark.DeleteBookmarkUseCase
import com.example.news_eat_fronted.domain.usecase.bookmark.PostBookmarkUseCase
import com.example.news_eat_fronted.domain.usecase.news.GetNewsDetailUseCase
import com.example.news_eat_fronted.domain.usecase.news.GetNewsSummaryUseCase
import com.example.news_eat_fronted.presentation.model.NewsDetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsDetailUseCase: GetNewsDetailUseCase,
    private val getNewsSummaryUseCase: GetNewsSummaryUseCase,
    private val postBookmarkUseCase: PostBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
) : ViewModel() {

    private val _newsId = MutableStateFlow<Long>(-1L)
    val newsId: StateFlow<Long> = _newsId

    private val _isBookmarked = MutableStateFlow<Boolean>(false)
    val isBookmarked: StateFlow<Boolean> = _isBookmarked

    private val _bookmarkId = MutableStateFlow<Long?>(null)
    val bookmarkId: StateFlow<Long?> = _bookmarkId

    private val _newsDetailState = MutableStateFlow<GetNewsDetailResponseEntity?>(null)
    val newsDetailState: StateFlow<GetNewsDetailResponseEntity?> = _newsDetailState

    private val _newsSummaryState = MutableStateFlow<NewsSummaryResponseEntity?>(null)
    val newsSummaryState: StateFlow<NewsSummaryResponseEntity?> = _newsSummaryState

    private val _postBookmarkState = MutableStateFlow<BookmarkIdResponseEntity?>(null)
    val postBookmarkState: StateFlow<BookmarkIdResponseEntity?> = _postBookmarkState

    private val _deleteBookmarkState = MutableSharedFlow<Unit?>()
    val deleteBookmarkState: SharedFlow<Unit?> = _deleteBookmarkState

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

    fun postBookmark() {
        viewModelScope.launch {
            try {
                val postBookmarkResponseEntity = postBookmarkUseCase(
                    newsId = _newsId.value
                )
                _postBookmarkState.value = postBookmarkResponseEntity
            } catch (ex: Exception) {}
        }
    }

    fun deleteBookmark() {
        viewModelScope.launch {
            try {
                deleteBookmarkUseCase(_bookmarkId.value!!)
                _deleteBookmarkState.emit(Unit)
            } catch (ex: Exception) {}
        }
    }

    fun setNewsId(id: Long) {
        _newsId.value = id
    }

    fun setBookmarked(isBookmarked: Boolean) {
        _isBookmarked.value = isBookmarked
    }

    fun setBookmarkId(id: Long) {
        _bookmarkId.value = id
    }
}
