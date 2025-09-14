package com.example.news_eat_fronted.presentation.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.bookmark.GetBookmarkListRequestEntity
import com.example.news_eat_fronted.domain.entity.response.bookmark.BookmarkResponseEntity
import com.example.news_eat_fronted.domain.usecase.bookmark.DeleteBookmarkUseCase
import com.example.news_eat_fronted.domain.usecase.bookmark.GetBookmarkListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarkListUseCase: GetBookmarkListUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase
)  : ViewModel() {

    private val _bookmarkListState = MutableStateFlow<List<BookmarkResponseEntity>>(emptyList())
    val bookmarkListState: StateFlow<List<BookmarkResponseEntity>> = _bookmarkListState

    private val _deleteBookmarkState = MutableSharedFlow<Unit?>()
    val deleteBookmarkState: SharedFlow<Unit?> = _deleteBookmarkState

    private var lastBookmarkId: Long = 0
    private var hasNext: Boolean = true
    private var isLoading: Boolean = false

    fun getBookmarkList(isLoadMore: Boolean = false, forceRefresh: Boolean = false) {
        if(!hasNext && !forceRefresh) return
        if(isLoading) return

        viewModelScope.launch {
            try {
                isLoading = true

                val getBookmarkListResponse = getBookmarkListUseCase(
                    getBookmarkListRequestEntity = GetBookmarkListRequestEntity(
                        lastBookmarkId = if(isLoadMore) lastBookmarkId else 0,
                        size = 10
                    )
                )
                getBookmarkListResponse.bookmarkResponseList.lastOrNull()?.bookmarkId?.let {
                    lastBookmarkId = it
                }
                hasNext = getBookmarkListResponse.hasNext

                _bookmarkListState.value =
                    if(isLoadMore) _bookmarkListState.value + getBookmarkListResponse.bookmarkResponseList
                    else getBookmarkListResponse.bookmarkResponseList

            } catch (ex: Exception) {
            } finally {
                isLoading = false
            }
        }
    }

    fun deleteBookmark(bookmarkId: Long) {
        viewModelScope.launch {
            try {
                deleteBookmarkUseCase(bookmarkId)
                _deleteBookmarkState.emit(Unit)

                _bookmarkListState.value = _bookmarkListState.value.filterNot { it.bookmarkId == bookmarkId }
            } catch (ex: Exception) {}
        }
    }
}