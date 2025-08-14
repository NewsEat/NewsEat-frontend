package com.example.news_eat_fronted.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivityLoginBinding
import com.example.news_eat_fronted.presentation.ui.signup.SignupActivity
import com.example.news_eat_fronted.util.CustomSnackBar
import com.example.news_eat_fronted.util.base.BindingActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loginViewModel = loginViewModel

        addListeners()
    }

    private fun addListeners() {
        binding.inputEmail.addTextChangedListener {
            loginViewModel.onEmailChanged(it.toString())
        }

        binding.inputPw.addTextChangedListener {
            loginViewModel.onPwChanged(it.toString())
        }

        binding.loginBtn.setOnClickListener {
            // 로그인 실패 시
            CustomSnackBar.make(binding.root, getString(R.string.snackbar_login_error)).show()
        }

        binding.gotoSingUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}