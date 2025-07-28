package com.example.news_eat_fronted.presentation.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ModifyViewModel: ViewModel() {
    private val _nickname = MutableStateFlow("")
    val nickname: StateFlow<String> = _nickname

    private val _nicknameLength = MutableStateFlow(0)
    val nicknameLength: StateFlow<Int> = _nicknameLength

    private val _selectedCategory = MutableStateFlow<List<String>>(emptyList())
    val selectedCategory: StateFlow<List<String>> = _selectedCategory

    fun onNicknameChanged(newNickname: String) {
        _nickname.value = newNickname
        _nicknameLength.value = newNickname.length
    }

    fun updateSelectedCategory(selectedList: List<String>) {
        _selectedCategory.value = selectedList
        Log.d("modifymodify", "modify하는중")
    }
}