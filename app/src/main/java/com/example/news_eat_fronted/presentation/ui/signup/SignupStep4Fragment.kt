package com.example.news_eat_fronted.presentation.ui.signup

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentSignupStep4Binding
import com.example.news_eat_fronted.util.base.BindingFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignupStep4Fragment: BindingFragment<FragmentSignupStep4Binding>(R.layout.fragment_signup_step4) {
    private val signupViewModel by activityViewModels<SignupViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)
            requireActivity().finish()
        }

        setMessage()
    }

    private fun setMessage() {
        val nickname = signupViewModel.nickname.value
        val message = "${nickname}님\n회원가입이 완료되었습니다! "

        val spannable = SpannableString(message).apply {
            val end = nickname.length
            setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.Primary600)),
                0, end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        binding.info.text = spannable
    }
}