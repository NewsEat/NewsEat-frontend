package com.example.news_eat_fronted.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.user.UpdateCategoryRequestEntity
import com.example.news_eat_fronted.domain.entity.request.user.UpdateNicknameRequestEntity
import com.example.news_eat_fronted.domain.usecase.user.GetMyPageProfileUseCase
import com.example.news_eat_fronted.domain.usecase.user.UpdateCategoryUseCase
import com.example.news_eat_fronted.domain.usecase.user.UpdateNicknameUseCase
import com.example.news_eat_fronted.domain.usecase.user.WithdrawUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val withdrawUseCase: WithdrawUseCase,
    private val getMyPageProfileUseCase: GetMyPageProfileUseCase,
    private val updateNicknameUseCase: UpdateNicknameUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase
): ViewModel() {

    private val _nickname = MutableStateFlow("")
    val nickname: StateFlow<String> = _nickname

    private val _interests = MutableStateFlow<List<String>>(emptyList())
    val interests: StateFlow<List<String>> = _interests

    private val _withdrawState = MutableSharedFlow<Unit?>()
    val withdrawState: SharedFlow<Unit?> = _withdrawState

    private val _updateNicknameState = MutableSharedFlow<Boolean>()
    val updateNicknameState: SharedFlow<Boolean> = _updateNicknameState

    private val _updateCategoryState = MutableSharedFlow<Boolean>()
    val updateCategoryState: SharedFlow<Boolean> = _updateCategoryState

    fun withdraw() {
        viewModelScope.launch {
            try {
                withdrawUseCase()
                _withdrawState.emit(Unit)
            } catch (ex: Exception) {}
        }
    }

    fun loadProfile() {
        viewModelScope.launch {
            try {
                val profile = getMyPageProfileUseCase()
                _nickname.value = profile.nickname
                _interests.value = profile.categories
            } catch (ex: Exception) {}
        }
    }

    fun updateNickname(newNickname: String) {
        viewModelScope.launch {
            try {
                updateNicknameUseCase(UpdateNicknameRequestEntity(newNickname))
                _nickname.value = newNickname
                _updateNicknameState.emit(true)
            } catch (ex: Exception) {
                _updateNicknameState.emit(false)
            }
        }
    }

    fun updateCategory(categoryIds: List<Int>) {
        viewModelScope.launch {
            try {
                val requestEntity = UpdateCategoryRequestEntity(categoryIds)
                updateCategoryUseCase(requestEntity)
                _updateCategoryState.emit(true)
            } catch (ex: Exception) {
                _updateCategoryState.emit(false)
            }
        }
    }
}
