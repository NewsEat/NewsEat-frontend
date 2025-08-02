package com.example.news_eat_fronted.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {

    private val _nickname = MutableStateFlow("쫀득물만두")
    val nickname: StateFlow<String> = _nickname

    private val _interests = MutableStateFlow<List<String>>(listOf("연예", "IT/과학", "세계"))
    val interests: StateFlow<List<String>> = _interests

    private val _navigateTo = MutableSharedFlow<MyPageNavigation>()
    val navigateTo: SharedFlow<MyPageNavigation> = _navigateTo

    fun onClickEditInterest() {
        viewModelScope.launch {
            _navigateTo.emit(MyPageNavigation.GoToEditInterest)
        }
    }

    fun onClickEditProfile() {
        viewModelScope.launch {
            _navigateTo.emit(MyPageNavigation.GoToEditProfile)
        }
    }

    fun onClickLogout() {
        viewModelScope.launch {
            _navigateTo.emit(MyPageNavigation.Logout)
        }
    }

    fun onClickWithdraw() {
        viewModelScope.launch {
            _navigateTo.emit(MyPageNavigation.Withdraw)
        }
    }

    sealed class MyPageNavigation {
        object GoToEditInterest : MyPageNavigation()
        object GoToEditProfile : MyPageNavigation()
        object Logout : MyPageNavigation()
        object Withdraw : MyPageNavigation()
    }
}
