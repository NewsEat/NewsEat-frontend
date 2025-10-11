package com.example.news_eat_fronted.presentation.ui.findpw

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_eat_fronted.domain.entity.request.auth.CheckEmailRequestEntity
import com.example.news_eat_fronted.domain.entity.request.auth.SendEmailRequestEntity
import com.example.news_eat_fronted.domain.entity.request.auth.VerifyResetPwRequestEntity
import com.example.news_eat_fronted.domain.entity.request.user.ModifyPwRequestEntity
import com.example.news_eat_fronted.domain.entity.response.auth.CheckEmailResponseEntity
import com.example.news_eat_fronted.domain.entity.response.auth.SendEmailResponseEntity
import com.example.news_eat_fronted.domain.entity.response.auth.VerifyResetPwResponseEntity
import com.example.news_eat_fronted.domain.usecase.auth.CheckEmailUseCase
import com.example.news_eat_fronted.domain.usecase.auth.ResetPwUseCase
import com.example.news_eat_fronted.domain.usecase.auth.SendEmailUseCase
import com.example.news_eat_fronted.domain.usecase.auth.VerifyResetPwUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindPwViewModel @Inject constructor(
    private val sendEmailUseCase: SendEmailUseCase,
    private val checkEmailUseCase: CheckEmailUseCase,
    private val verifyResetPwUseCase: VerifyResetPwUseCase,
    private val resetPwUseCase: ResetPwUseCase
): ViewModel() {
    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep

    private val _isNextBtnEnabled = MutableStateFlow(false)
    val isNextBtnEnabled: StateFlow<Boolean> = _isNextBtnEnabled

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _emailVerifyCode = MutableStateFlow("")
    val emailVerifyCode: StateFlow<String> = _emailVerifyCode

    private val _sendEmailState = MutableStateFlow<SendEmailResponseEntity?>(null)
    val sendEmailState: StateFlow<SendEmailResponseEntity?> = _sendEmailState.asStateFlow()

    private val _emailAuthId = MutableStateFlow(0)

    private val _userId = MutableStateFlow(0)

    private val _checkEmailState = MutableStateFlow<CheckEmailResponseEntity?>(null)
    val checkEmailState: StateFlow<CheckEmailResponseEntity?> = _checkEmailState.asStateFlow()

    private val _isTimeOver = MutableStateFlow(false)
    val isTimeOver: StateFlow<Boolean> =  _isTimeOver

    private val _isErrorVerify = MutableStateFlow<Boolean>(false)
    val isErrorVerify: StateFlow<Boolean> = _isErrorVerify

    private val _isSuccessVerify =MutableStateFlow<Boolean>(false)

    private val _verifyResetPw = MutableStateFlow<VerifyResetPwResponseEntity?>(null)
    val verifyResetPw: StateFlow<VerifyResetPwResponseEntity?> = _verifyResetPw.asStateFlow()

    private val _modifyPwState = MutableSharedFlow<Unit?>()
    val modifyPwState: SharedFlow<Unit?> = _modifyPwState

    fun sendEmail() {
        viewModelScope.launch {
            try {
                val sendEmailResponseEntity = sendEmailUseCase(
                    sendEmailRequestEntity = SendEmailRequestEntity(
                        email = _email.value,
                        purpose = 3
                    )
                )
                _sendEmailState.value = sendEmailResponseEntity
            } catch (ex:Exception) { }
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

    fun verifyResetPw() {
        viewModelScope.launch {
            try {
                val verifyResetPwResponseEntity = verifyResetPwUseCase(
                    verifyResetPwRequestEntity = VerifyResetPwRequestEntity(
                        emailAuthId = _emailAuthId.value.toLong()
                    )
                )
                _verifyResetPw.value = verifyResetPwResponseEntity
            } catch (ex:Exception) {}
        }
    }

    fun resetPw(pw: String, pwConfirm: String) {
        viewModelScope.launch {
            try {
                resetPwUseCase(ModifyPwRequestEntity(
                    userId = _userId.value,
                    password = pw,
                    confirmPassword = pwConfirm
                ))
                _modifyPwState.emit(Unit)
            } catch (ex: Exception) {}
        }
    }

    fun goNextStep() {
        if (_currentStep.value < 1) {
            _currentStep.value += 1
        }
    }

    fun decreaseCurrentStep() {
        _currentStep.value -= 1
    }

    fun updateNextEnabled(enabled: Boolean) {
        _isNextBtnEnabled.value = enabled
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
        updateEnabled()
    }

    fun onEmailVerifyCodeChanged(newCode: String) {
        _emailVerifyCode.value = newCode
        updateEnabled()
    }

    fun updateTimeOver(isTimeOver: Boolean) {
        _isTimeOver.value = isTimeOver
    }

    fun setEmailVerifySuccess() {
        _isErrorVerify.value = false
        _isSuccessVerify.value = true
        updateEnabled()
    }

    private fun updateEnabled() {
        _isNextBtnEnabled.value = _email.value.isNotEmpty()
                && _emailVerifyCode.value.isNotEmpty()
                && !_isErrorVerify.value
                && _isSuccessVerify.value
    }

    fun setEmailAuthId(id: Int) {
        _emailAuthId.value = id
    }

    fun setUserId(id: Int) {
        _userId.value = id
    }
}