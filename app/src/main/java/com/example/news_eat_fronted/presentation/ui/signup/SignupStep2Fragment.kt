package com.example.news_eat_fronted.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentSignupStep2Binding
import com.example.news_eat_fronted.presentation.ui.mypage.ModifyViewModel
import com.example.news_eat_fronted.util.base.BindingFragment
import com.example.news_eat_fronted.util.setupKeyboardHide
import kotlinx.coroutines.launch

class SignupStep2Fragment: BindingFragment<FragmentSignupStep2Binding>(R.layout.fragment_signup_step2) {
    private val signupViewModel by activityViewModels<SignupViewModel>()
    private val modifyViewModel by activityViewModels<ModifyViewModel>()
    private var isModify: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupViewModel = signupViewModel

        isModify = arguments?.getBoolean("isModify") ?: false

        collectData()
        addListeners()
        setModifyView()
        setupKeyboardHide()
    }

    private fun collectData() {
        val flow = if (isModify) {
            modifyViewModel.nicknameLength
        } else {
            signupViewModel.nicknameLength
        }
        viewLifecycleOwner.lifecycleScope.launch {
            flow.collect { nicknameLength ->
                binding.nicknameLength.text = "$nicknameLength/10"
            }
        }
    }

    private fun addListeners() {
        binding.inputNickname.addTextChangedListener {
            if(isModify) {
                modifyViewModel.onNicknameChanged(it.toString())
            } else {
                signupViewModel.onNicknameChanged(it.toString())
            }
        }
    }

    private fun setModifyView() {
        binding.titleNickname.text = getString(R.string.sign_up_nickname)
    }
}