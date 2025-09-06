package com.example.news_eat_fronted.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.auth.CheckEmailRequestEntity
import com.example.news_eat_fronted.domain.entity.request.auth.SendEmailRequestEntity
import com.example.news_eat_fronted.domain.entity.request.auth.SignupRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.CheckEmailResponseEntity
import com.example.news_eat_fronted.domain.entity.response.auth.SendEmailResponseEntity
import com.example.news_eat_fronted.domain.entity.response.auth.SignupResponseEntity
import com.example.news_eat_fronted.domain.usecase.auth.CheckEmailUseCase
import com.example.news_eat_fronted.domain.usecase.auth.SendEmailUseCase
import com.example.news_eat_fronted.domain.usecase.auth.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val sendEmailUseCase: SendEmailUseCase,
    private val checkEmailUseCase: CheckEmailUseCase,
    private val signupUseCase: SignupUseCase
): ViewModel() {
    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _emailVerifyCode = MutableStateFlow("")
    val emailVerifyCode: StateFlow<String> = _emailVerifyCode

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

    private val _nickname = MutableStateFlow("")
    val nickname: StateFlow<String> = _nickname

    private val _nicknameLength = MutableStateFlow(0)
    val nicknameLength: StateFlow<Int> = _nicknameLength

    private val _selectedCategory = MutableStateFlow<List<Int>>(emptyList())
    val selectedCategory: StateFlow<List<Int>> = _selectedCategory

    private val _isPwVisible = MutableStateFlow(false)
    val isPwVisible: StateFlow<Boolean> = _isPwVisible

    private val _isPwConfirmVisible = MutableStateFlow(false)
    val isPwConfirmVisible: StateFlow<Boolean> = _isPwConfirmVisible

    private val _isTimeOver = MutableStateFlow(false)
    val isTimeOver: StateFlow<Boolean> =  _isTimeOver

    private val _sendEmailState = MutableStateFlow<SendEmailResponseEntity?>(null)
    val sendEmailState: StateFlow<SendEmailResponseEntity?> = _sendEmailState.asStateFlow()

    private val _emailAuthId = MutableStateFlow(0)
    val emailAuthId: StateFlow<Int> = _emailAuthId

    private val _checkEmailState = MutableStateFlow<CheckEmailResponseEntity?>(null)
    val checkEmailState: StateFlow<CheckEmailResponseEntity?> = _checkEmailState.asStateFlow()

    private val _isErrorVerify = MutableStateFlow<Boolean>(false)
    val isErrorVerify: StateFlow<Boolean> = _isErrorVerify

    private val _isSuccessVerify =MutableStateFlow<Boolean>(false)
    val isSuccessVerify: StateFlow<Boolean> = _isSuccessVerify

    private val _signupState = MutableStateFlow<SignupResponseEntity?>(null)
    val signupState: StateFlow<SignupResponseEntity?> = _signupState.asStateFlow()


    fun sendEmail() {
        viewModelScope.launch {
            try {
                val sendEmailResponseEntity = sendEmailUseCase(
                    sendEmailRequestEntity = SendEmailRequestEntity(
                        email = _email.value,
                        purpose = 1
                    )
                )
                _sendEmailState.value = sendEmailResponseEntity
            } catch (ex:Exception) {}
        }
    }

    fun checkEmail() {
        viewModelScope.launch {
            try {
                val checkEmailResponseEntity = checkEmailUseCase(
                    checkEmailRequestEntity = CheckEmailRequestEntity(
                        emailAuthId = _emailAuthId.value,
                        emailAuthCode = _emailVerifyCode.value
                    )
                )
                _checkEmailState.value = checkEmailResponseEntity
            } catch (ex:Exception) {
                _isErrorVerify.value = true
                updateEnabled()
            }
        }
    }

    fun signup() {
        viewModelScope.launch {
            try {
                val signupResponseEntity = signupUseCase(
                    signupRequestEntity = SignupRequestEntity(
                        emailAuthId = _emailAuthId.value,
                        email = _email.value,
                        password = _pw.value,
                        nickname = _nickname.value,
                        categoryIds = _selectedCategory.value
                    )
                )
                _signupState.value = signupResponseEntity
            } catch (ex: Exception) {}
        }
    }

    fun goNextStep() {
        if (_currentStep.value < 3) {
            _currentStep.value += 1
        }
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
        updateEnabled()
    }

    fun onEmailVerifyCodeChanged(newCode: String) {
        _emailVerifyCode.value = newCode
        updateEnabled()
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

    fun updateNextEnabled(enabled: Boolean) {
        _isNextBtnEnabled.value = enabled
    }

    fun onNicknameChanged(newNickname: String) {
        _nickname.value = newNickname
        _nicknameLength.value = newNickname.length
        updateEnabledForNickname()
    }

    fun updateSelectedCategory(selectedList: List<Int>) {
        _selectedCategory.value = selectedList
        updateEnabledForCategory()
    }

    fun decreaseCurrentStep() {
        _currentStep.value -= 1
    }

    private fun validatePw(pw: String): Boolean {
        val regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#\$%^&*()~_\\-+=<>?]{8,16}$")
        return regex.matches(pw)
    }

    private fun validatePwConfirm(pw: String, pwConfirm: String) {
        _isPwConfirmValid.value = pw.isNotEmpty() && pw == pwConfirm
    }

    private fun updateEnabled() {
        _isNextBtnEnabled.value = _email.value.isNotEmpty()
                && _emailVerifyCode.value.isNotEmpty()
                && _pw.value.isNotEmpty()
                && _pwConfirm.value.isNotEmpty()
                && _isPwValid.value
                && _isPwConfirmValid.value
                && !_isErrorVerify.value
                && _isSuccessVerify.value
    }

    private fun updateEnabledForNickname() {
        _isNextBtnEnabled.value = _nickname.value.isNotEmpty()
    }

    private fun updateEnabledForCategory() {
        _isNextBtnEnabled.value = _selectedCategory.value.isNotEmpty()
    }

    fun updateTimeOver(isTimeOver: Boolean) {
        _isTimeOver.value = isTimeOver
    }

    fun togglePwVisible() {
        _isPwVisible.value = !_isPwVisible.value
    }

    fun togglePwConfirmVisible() {
        _isPwConfirmVisible.value = !_isPwConfirmVisible.value
    }

    fun setEmailAuthId(id: Int) {
        _emailAuthId.value = id
    }

    fun setEmailVerifySuccess() {
        _isErrorVerify.value = false
        _isSuccessVerify.value = true
        updateEnabled()
    }
}