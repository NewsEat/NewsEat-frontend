package com.example.news_eat_fronted.presentation.ui.findpw

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_eat_fronted.util.base.BindingFragment
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentFindPwBinding
import com.example.news_eat_fronted.util.CustomSnackBar
import com.example.news_eat_fronted.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class FindPwFragment: BindingFragment<FragmentFindPwBinding>(R.layout.fragment_find_pw) {
    private val findPwViewModel: FindPwViewModel by activityViewModels()
    private var countDownTimer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectData()
        addListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer?.cancel()
        countDownTimer = null
    }

    private fun collectData() {
        // 이메일 empty 확인
        lifecycleScope.launch {
            findPwViewModel.email.collect { email ->
                binding.btnVerifyEmail.isEnabled = email.isNotEmpty()
            }
        }
        // 인증번호 empty 확인
        lifecycleScope.launch {
            findPwViewModel.emailVerifyCode.collect { code ->
                binding.btnConfirmCode.isEnabled = code.isNotEmpty()
            }
        }

        // 이메일 전송
        lifecycleScope.launch {
            findPwViewModel.sendEmailState.collect { sendEmailState ->
                sendEmailState?.let {
                    CustomSnackBar.make(binding.root, getString(R.string.email_verify_sent)).show()
                    findPwViewModel.setEmailAuthId(it.emailAuthId)
                }
            }
        }

        // 이메일 인증
        lifecycleScope.launch {
            findPwViewModel.isErrorVerify.collect { isError ->
                if(findPwViewModel.isErrorVerify.value) {
                    binding.infoEmailError.visibility = View.VISIBLE
                    binding.infoEmailSuccess.visibility = View.GONE
                }
            }
        }

        lifecycleScope.launch {
            findPwViewModel.checkEmailState.collect { checkEmailState ->
                if(checkEmailState?.isChecked == true) {
                    binding.infoEmailSuccess.visibility = View.VISIBLE
                    binding.infoEmailError.visibility = View.GONE
                    findPwViewModel.setEmailVerifySuccess()
                    binding.timer.visibility = View.GONE
                }
            }
        }
    }

    private fun addListeners() {
        binding.inputEmail.addTextChangedListener {
            findPwViewModel.onEmailChanged(it.toString())
        }

        binding.inputVerifyCode.addTextChangedListener {
            findPwViewModel.onEmailVerifyCodeChanged(it.toString())
        }

        binding.btnVerifyEmail.setOnClickListener {
            it.hideKeyboard()
            setTimer()
            findPwViewModel.sendEmail()  // 인증번호 전송 API
            binding.btnVerifyEmail.text = getString(R.string.button_resend)
        }

        binding.btnConfirmCode.setOnClickListener {
            it.hideKeyboard()
            if(findPwViewModel.isTimeOver.value) {
                CustomSnackBar.make(binding.root, getString(R.string.sanckbar_time_out)).show()
            }
            else {
                findPwViewModel.checkEmail()  // 인증번호 검증 API
            }
        }
    }

    private fun setTimer() {
        countDownTimer?.cancel()

        binding.timer.visibility = View.VISIBLE

        val totalTime = (3 * 60 + 1) * 1000L
        val interval = 1000L

        countDownTimer = object : CountDownTimer(totalTime, interval) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60

                binding.timer.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                findPwViewModel.updateTimeOver(true) // timeOver 인 경우
            }
        }.start()
    }
}