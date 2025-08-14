package com.example.news_eat_fronted.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.example.news_eat_fronted.MainActivity
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivityLoginBinding
import com.example.news_eat_fronted.presentation.ui.signup.SignupActivity
import com.example.news_eat_fronted.util.CustomSnackBar
import com.example.news_eat_fronted.util.base.BindingActivity

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val loginViewModel by viewModels<LoginViewModel>()
    var isPwVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loginViewModel = loginViewModel

        addListeners()
        setPwVisibility()
    }

    private fun addListeners() {
        binding.inputEmail.addTextChangedListener {
            loginViewModel.onEmailChanged(it.toString())
        }

        binding.inputPw.addTextChangedListener {
            loginViewModel.onPwChanged(it.toString())
            binding.showPwBtn.visibility = if(it.isNullOrEmpty()) View.GONE else View.VISIBLE
        }

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            // 로그인 실패 시
//            CustomSnackBar.make(binding.root, getString(R.string.snackbar_login_error)).show()
        }

        binding.gotoSingUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun setPwVisibility() {
        binding.showPwBtn.setOnClickListener {
            isPwVisible = !isPwVisible
            val selection = binding.inputPw.selectionStart

            if(isPwVisible) {
                binding.inputPw.inputType = InputType.TYPE_CLASS_TEXT
                binding.showPwBtn.setImageResource(R.drawable.btn_pw_visible)
            }
            else {
                binding.inputPw.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.showPwBtn.setImageResource(R.drawable.btn_pw_invisible)
            }

            binding.inputPw.setSelection(selection)
        }
    }
}