package com.example.news_eat_fronted.presentation.ui.findpw

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivityFindPwBinding
import com.example.news_eat_fronted.presentation.ui.mypage.ModifyPwFragment
import com.example.news_eat_fronted.presentation.ui.mypage.ModifyViewModel
import com.example.news_eat_fronted.util.base.BindingActivity
import com.example.news_eat_fronted.util.setupKeyboardHide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FindPwActivity : BindingActivity<ActivityFindPwBinding>(R.layout.activity_find_pw) {
    private val findPwViewModel by viewModels<FindPwViewModel>()
    private val modifyViewModel by viewModels<ModifyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupKeyboardHide()
        collectData()
        addListeners()
    }

    private fun collectData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    findPwViewModel.currentStep.collect { step ->
                        showStepFragment(step)
                        handleVisibility(step)
                    }
                }

                launch {
                    findPwViewModel.isNextBtnEnabled.collect { enabled ->
                        if(findPwViewModel.currentStep.value == 0) {
                            binding.btnNext.isEnabled = enabled
                        }
                    }
                }

                launch {
                    modifyViewModel.isNextBtnEnabled.collect { enabled ->
                        if(findPwViewModel.currentStep.value == 1) {
                            binding.btnNext.isEnabled = enabled
                        }
                    }
                }

                launch {
                    findPwViewModel.verifyResetPw.collect { state ->
                        state?.let { findPwViewModel.setUserId(it.userId) }
                    }
                }

                launch {
                    findPwViewModel.modifyPwState.collect { state ->
                        val resultIntent = Intent().apply {
                            putExtra("pwChanged", true)
                        }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                }
            }
        }
    }

    private fun addListeners() {
        binding.btnNext.setOnClickListener {
            if(findPwViewModel.currentStep.value == 0) {
                findPwViewModel.verifyResetPw()
                findPwViewModel.goNextStep()
                findPwViewModel.updateNextEnabled(false)
            }
            else if(findPwViewModel.currentStep.value == 1) {
                // 비밀번호 재설정 API
                findPwViewModel.resetPw(modifyViewModel.pw.value, modifyViewModel.pwConfirm.value)
            }
        }

        binding.btnBack.setOnClickListener {
            if(findPwViewModel.currentStep.value == 0) {
                finish()
            }
            else {
                findPwViewModel.decreaseCurrentStep()
            }
        }
    }

    private fun showStepFragment(step: Int) {
        val fragment = when(step) {
            0 -> FindPwFragment()
            else -> ModifyPwFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fcvFindPw.id, fragment)
            .commit()
    }

    private fun handleVisibility(step: Int) {
        if(step == 1) {
            binding.btnNext.text = getString(R.string.button_finish)
            binding.btnNext.isEnabled = false
        }
    }
}