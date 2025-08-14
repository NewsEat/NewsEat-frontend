package com.example.news_eat_fronted.presentation.ui.signup

import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputType
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentSignupStep1Binding
import com.example.news_eat_fronted.util.base.BindingFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_eat_fronted.util.CustomSnackBar
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


class SignupStep1Fragment : BindingFragment<FragmentSignupStep1Binding>(R.layout.fragment_signup_step1) {
    private val signupViewModel by activityViewModels<SignupViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListeners()
        collectData()
        setPwVisibility()
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
            binding.showPwBtn.visibility = if(it.isNullOrEmpty()) View.GONE else View.VISIBLE
        }

        binding.inputPwConfirm.addTextChangedListener {
            signupViewModel.onPwConfirmChanged(it.toString())
            binding.showPwConfirmBtn.visibility = if(it.isNullOrEmpty()) View.GONE else View.VISIBLE
        }

        binding.btnVerifyEmail.setOnClickListener {
            setTimer()
        }

        binding.btnConfirmCode.setOnClickListener {
            if(signupViewModel.isTimeOver.value) {
                CustomSnackBar.make(binding.root, getString(R.string.sanckbar_time_out)).show()
            }
            else {
                // 인증번호 검증 API
            }
        }
    }

    private fun setPwVisibility() {
        binding.showPwBtn.setOnClickListener {
            signupViewModel.togglePwVisible()

            val selection = binding.inputPw.selectionStart

            if(signupViewModel.isPwVisible.value) {
                binding.inputPw.inputType = InputType.TYPE_CLASS_TEXT
                binding.showPwBtn.setImageResource(R.drawable.btn_pw_visible)
            }
            else {
                binding.inputPw.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.showPwBtn.setImageResource(R.drawable.btn_pw_invisible)
            }

            binding.inputPw.setSelection(selection)
        }

        binding.showPwConfirmBtn.setOnClickListener {
            signupViewModel.togglePwConfirmVisible()

            val selection = binding.inputPwConfirm.selectionStart

            if(signupViewModel.isPwConfirmVisible.value) {
                binding.inputPwConfirm.inputType = InputType.TYPE_CLASS_TEXT
                binding.showPwConfirmBtn.setImageResource(R.drawable.btn_pw_visible)
            }
            else {
                binding.inputPwConfirm.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.showPwConfirmBtn.setImageResource(R.drawable.btn_pw_invisible)
            }

            binding.inputPwConfirm.setSelection(selection)
        }
    }

    private fun setTimer() {
        binding.timer.visibility = View.VISIBLE

        val totalTime = (3 * 60 + 1) * 1000L
        val interval = 1000L

        object : CountDownTimer(totalTime, interval) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60

                binding.timer.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                signupViewModel.updateTimeOver(true) // timeOver 인 경우
            }
        }.start()
    }
}