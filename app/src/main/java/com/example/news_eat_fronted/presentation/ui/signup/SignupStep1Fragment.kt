package com.example.news_eat_fronted.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentSignupStep1Binding
import com.example.news_eat_fronted.util.base.BindingFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


class SignupStep1Fragment : BindingFragment<FragmentSignupStep1Binding>(R.layout.fragment_signup_step1) {
    private val signupViewModel by activityViewModels<SignupViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListeners()
        collectData()
    }

    private fun collectData() {
        // 이메일 empty 확인
        lifecycleScope.launch {
            signupViewModel.email.collect { email ->
                binding.btnVerifyEmail.isEnabled = email.isNotEmpty()
            }
        }
        // 인증번호 empty 확인
        lifecycleScope.launch {
            signupViewModel.emailVerifyCode.collect { code ->
                binding.btnConfirmCode.isEnabled = code.isNotEmpty()
            }
        }
        // 비밀번호 정규식 확인
        lifecycleScope.launch {
            combine(signupViewModel.pw, signupViewModel.isPwValid) { pw, isValid ->
                pw to isValid
            }.collect { (pw, isValid) ->
                if (pw.isNotEmpty()) {
                    binding.infoPwError1.visibility = if (isValid) View.GONE else View.VISIBLE
                } else {
                    binding.infoPwError1.visibility = View.GONE
                }
            }
        }
        // 비밀번호 동일입력 확인
        lifecycleScope.launch {
            combine(signupViewModel.pw,
                signupViewModel.pwConfirm,
                signupViewModel.isPwConfirmValid) { pw, pwConfirm, isPwConfirmValid ->
                Triple(pw, pwConfirm, isPwConfirmValid)
            }.collect{ (pw, pwConfirm, isPwConfirmValid) ->
                if(pw.isNotEmpty() && pwConfirm.isNotEmpty()) {
                    binding.infoPwError2.visibility = if (isPwConfirmValid) View.GONE else View.VISIBLE
                } else {
                    binding.infoPwError2.visibility = View.GONE
                }
            }
        }
    }

    private fun addListeners() {
        binding.inputEmail.addTextChangedListener {
            signupViewModel.onEmailChanged(it.toString())
        }

        binding.inputVerifyCode.addTextChangedListener {
            signupViewModel.onEmailVerifyCodeChanged(it.toString())
        }

        binding.inputPw.addTextChangedListener {
            signupViewModel.onPwChanged(it.toString())
        }

        binding.inputPwConfirm.addTextChangedListener {
            signupViewModel.onPwConfirmChanged(it.toString())
        }
    }
}