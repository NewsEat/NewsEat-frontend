package com.example.news_eat_fronted.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyPageViewModel : ViewModel() {

    private val _nickname = MutableStateFlow("쫀득물만두")
    val nickname: StateFlow<String> = _nickname

    private val _interests = MutableStateFlow<List<String>>(listOf("연예", "IT/과학", "세계"))
    val interests: StateFlow<List<String>> = _interests

}
