package com.example.news_eat_fronted.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentSignupStep2Binding
import com.example.news_eat_fronted.util.base.BindingFragment

class SignupStep2Fragment: BindingFragment<FragmentSignupStep2Binding>(R.layout.fragment_signup_step2) {
    private val signupViewModel by activityViewModels<SignupViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupViewModel = signupViewModel

        addListeners()
    }

    private fun addListeners() {
        binding.inputNickname.addTextChangedListener {
            signupViewModel.onNicknameChanged(it.toString())
        }
    }
}