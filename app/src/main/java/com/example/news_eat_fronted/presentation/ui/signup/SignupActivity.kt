package com.example.news_eat_fronted.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivitySignUpBinding
import com.example.news_eat_fronted.util.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val signupViewModel by viewModels<SignupViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectData()
        addListeners()
    }

    private fun collectData() {
        lifecycleScope.launch {
            signupViewModel.currentStep.collect { step ->
                updateIndicator(step)
                showStepFragment(step)
                handleVisibility(step)
            }
        }

        lifecycleScope.launch {
            signupViewModel.isNextBtnEnabled.collect { enabled ->
                binding.btnSignUp.isEnabled = enabled
            }
        }
    }

    private fun addListeners() {
        binding.btnSignUp.setOnClickListener {
            signupViewModel.goNextStep()
            signupViewModel.updateNextEnabled(false)

            if(signupViewModel.currentStep.value == 3) {
                // 회원가입 API
            }
        }

        binding.btnBack.setOnClickListener {
            if(signupViewModel.currentStep.value == 0) {
                finish()
            }
            else {
                signupViewModel.decreaseCurrentStep()
                signupViewModel.onEmailChanged("")
                signupViewModel.onEmailVerifyCodeChanged("")
                signupViewModel.onPwChanged("")
                signupViewModel.onPwConfirmChanged("")
                signupViewModel.onNicknameChanged("")
            }
        }
    }

    private fun updateIndicator(step: Int) {
        val progressValue = when(step) {
            0 -> 25f
            1 -> 50f
            2 -> 75f
            3 -> 100f
            else -> 25f
        }

        binding.progressBar.apply {
            progress = progressValue
        }
    }

    private fun showStepFragment(step: Int) {
        val fragment = when(step) {
            0 -> SignupStep1Fragment()
            1 -> SignupStep2Fragment()
            2 -> SignupStep3Fragment()
            else -> SignupStep4Fragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fcvSignUp.id, fragment)
            .commit()
    }

    private fun handleVisibility(step: Int) {
        if(step == 2) {
            binding.btnSignUp.text = getString(R.string.button_finish)
        }
        else if(step == 3) {
            binding.btnSignUp.visibility = View.GONE
            binding.btnBack.visibility = View.GONE
        }
    }
}