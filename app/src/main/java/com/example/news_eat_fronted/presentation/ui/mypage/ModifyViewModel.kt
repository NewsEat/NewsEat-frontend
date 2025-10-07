package com.example.news_eat_fronted.presentation.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.user.ModifyPwRequestEntity
import com.example.news_eat_fronted.domain.usecase.user.ModifyPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyViewModel @Inject constructor(
    private val modifyPasswordUseCase: ModifyPasswordUseCase
): ViewModel() {
    private val _nickname = MutableStateFlow("")
    val nickname: StateFlow<String> = _nickname

    private val _nicknameLength = MutableStateFlow(0)
    val nicknameLength: StateFlow<Int> = _nicknameLength

    private val _selectedCategory = MutableStateFlow<List<Int>>(emptyList())
    val selectedCategory: StateFlow<List<Int>> = _selectedCategory

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

    private val _isPwVisible = MutableStateFlow(false)
    val isPwVisible: StateFlow<Boolean> = _isPwVisible

    private val _isPwConfirmVisible = MutableStateFlow(false)
    val isPwConfirmVisible: StateFlow<Boolean> = _isPwConfirmVisible

    private var originalNickname: String = ""

    private var originalCategories: List<Int> = emptyList()

    private val _currentSpeed = MutableStateFlow(0f)
    val currentSpeed: StateFlow<Float> = _currentSpeed

    private val _currentPitch = MutableStateFlow(0f)
    val currentPitch: StateFlow<Float> = _currentPitch

    private val _modifyPwState = MutableSharedFlow<Unit?>()
    val modifyPwState: SharedFlow<Unit?> = _modifyPwState

    fun setOriginalNickname(original: String) {
        originalNickname = original
        updateEnabledForNickname()
    }

    fun onNicknameChanged(newNickname: String) {
        _nickname.value = newNickname
        _nicknameLength.value = newNickname.length
        updateEnabledForNickname()
    }

    fun updateSelectedCategory(selectedList: ArrayList<Int>?) {
        _selectedCategory.value = selectedList ?: emptyList()
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
        _isNextBtnEnabled.value =
            _nickname.value.isNotEmpty() && _nickname.value != originalNickname
    }

    private fun updateEnabledForCategory() {
        _isNextBtnEnabled.value =
            _selectedCategory.value.isNotEmpty() &&
            !_selectedCategory.value.containsAll(originalCategories) ||
            !originalCategories.containsAll(_selectedCategory.value)
    }

    fun togglePwVisible() {
        _isPwVisible.value = !_isPwVisible.value
    }

    fun togglePwConfirmVisible() {
        _isPwConfirmVisible.value = !_isPwConfirmVisible.value
    }

    fun setOriginalCategories(original: List<Int>?) {
        originalCategories = original ?: emptyList()
        updateEnabledForCategory()
    }

    fun setCurrentSpeed(speed: Float) {
        _currentSpeed.value = speed
    }

    fun setCurrentPitch(pitch: Float) {
        _currentPitch.value = pitch
    }

    fun setForceEnableNextBtn() {
        _isNextBtnEnabled.value = true
    }

    fun modifyPassword() {
        viewModelScope.launch {
            try {
                modifyPasswordUseCase(ModifyPwRequestEntity(
                    password = _pw.value,
                    confirmPassword = _pwConfirm.value
                ))
                _modifyPwState.emit(Unit)
            } catch (ex: Exception) {}
        }
    }
}