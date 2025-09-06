package com.example.news_eat_fronted.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val withdrawUseCase: WithdrawUseCase
): ViewModel() {

    private val _nickname = MutableStateFlow("쫀득물만두")
    val nickname: StateFlow<String> = _nickname

    private val _interests = MutableStateFlow<List<String>>(listOf("연예", "IT/과학", "세계"))
    val interests: StateFlow<List<String>> = _interests

    private val _withdrawState = MutableSharedFlow<Unit?>()
    val withdrawState: SharedFlow<Unit?> = _withdrawState

    fun withdraw() {
        viewModelScope.launch {
            try {
                withdrawUseCase()
                _withdrawState.emit(Unit)
            } catch (ex: Exception) {}
        }
    }
}
