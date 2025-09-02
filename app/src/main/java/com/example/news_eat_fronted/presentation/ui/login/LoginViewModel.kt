package com.example.news_eat_fronted.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.auth.LoginRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.LoginResponseEntity
import com.example.news_eat_fronted.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
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

    private val _loginState = MutableStateFlow<LoginResponseEntity?>(null)
    val loginState: StateFlow<LoginResponseEntity?> = _loginState.asStateFlow()

    fun login() {
        viewModelScope.launch {
            try {
                val loginResponseEntity = loginUseCase(
                    loginRequestEntity = LoginRequestEntity(
                        email = _email.value,
                        password = _pw.value
                    )
                )
                _loginState.value = loginResponseEntity
            } catch (ex: Exception) {}
        }
    }

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