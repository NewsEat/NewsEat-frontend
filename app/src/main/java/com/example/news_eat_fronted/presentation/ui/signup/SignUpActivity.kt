package com.example.news_eat_fronted.presentation.ui.signup

import android.os.Bundle
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivitySignUpBinding
import com.example.news_eat_fronted.util.base.BindingActivity

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private var currentStep = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateIndicator(currentStep)
        showStepFragment(currentStep)

        binding.btnSignUp.setOnClickListener {
            if(currentStep < 3) {
                currentStep += 1
                updateIndicator(currentStep)
                showStepFragment(currentStep)
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
            else -> SignupStep1Fragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fcvSignUp.id, fragment)
            .commit()
    }
}