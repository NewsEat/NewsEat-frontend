package com.example.news_eat_fronted.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class LoginViewModel: ViewModel() {
    private  val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private  val _pw = MutableStateFlow("")
    val pw: StateFlow<String> = _pw

    private val _isPwVisible = MutableStateFlow(false)
    val isPwVisible: StateFlow<Boolean> = _isPwVisible

    val isLoginEnabled: StateFlow<Boolean> = combine(_email, _pw) { email, pw ->
        email.isNotEmpty() && pw.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val isLoginEnabledLiveData: LiveData<Boolean> = isLoginEnabled.asLiveData()


    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPwChanged(newPw: String) {
        _pw.value = newPw
    }

    fun togglePwVisible() {
        _isPwVisible.value = !_isPwVisible.value
    }
}