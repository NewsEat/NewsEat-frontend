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

    private val _pw = MutableStateFlow("")
    val pw: StateFlow<String> = _pw

    private val _pwConfirm = MutableStateFlow("")
    val pwConfirm: StateFlow<String> = _pwConfirm

    private val _isPwValid = MutableStateFlow(false)
    val isPwValid: StateFlow<Boolean> = _isPwValid

    private val _isPwConfirmValid = MutableStateFlow(false)
    val isPwConfirmValid: StateFlow<Boolean> = _isPwConfirmValid

    private val _isNextBtnEnabled = MutableStateFlow(false)
    val isNextBtnEnabled: StateFlow<Boolean> = _isNextBtnEnabled

    fun onNicknameChanged(newNickname: String) {
        _nickname.value = newNickname
        _nicknameLength.value = newNickname.length
        updateEnabledForNickname()
    }

    fun updateSelectedCategory(selectedList: List<String>) {
        _selectedCategory.value = selectedList
        updateEnabledForCategory()
    }

    fun onPwChanged(newPw: String) {
        _pw.value = newPw
        _isPwValid.value = validatePw(newPw)
        validatePwConfirm(_pw.value, _pwConfirm.value)
        updateEnabled()
    }

    fun onPwConfirmChanged(newPwConfirm: String) {
        _pwConfirm.value = newPwConfirm
        validatePwConfirm(_pw.value, _pwConfirm.value)
        updateEnabled()
    }

    private fun validatePw(pw: String): Boolean {
        val regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#\$%^&*()~_\\-+=<>?]{8,16}$")
        return regex.matches(pw)
    }

    private fun validatePwConfirm(pw: String, pwConfirm: String) {
        _isPwConfirmValid.value = pw.isNotEmpty() && pw == pwConfirm
    }

    private fun updateEnabled() {
        _isNextBtnEnabled.value = _pw.value.isNotEmpty()
                && _pwConfirm.value.isNotEmpty()
                && _isPwValid.value
                && _isPwConfirmValid.value
    }

    private fun updateEnabledForNickname() {
        _isNextBtnEnabled.value = _nickname.value.isNotEmpty()
    }

    private fun updateEnabledForCategory() {
        _isNextBtnEnabled.value = _selectedCategory.value.isNotEmpty()
    }
}