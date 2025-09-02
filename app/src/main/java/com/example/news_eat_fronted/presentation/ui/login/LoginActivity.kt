package com.example.news_eat_fronted.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.news_eat_fronted.MainActivity
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.data.token.TokenManager
import com.example.news_eat_fronted.databinding.ActivityLoginBinding
import com.example.news_eat_fronted.presentation.ui.signup.SignupActivity
import com.example.news_eat_fronted.util.CustomSnackBar
import com.example.news_eat_fronted.util.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val loginViewModel by viewModels<LoginViewModel>()
    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loginViewModel = loginViewModel

        collectData()
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
            loginViewModel.login()  // 로그인 API
            // 로그인 실패 시
//            CustomSnackBar.make(binding.root, getString(R.string.snackbar_login_error)).show()
        }

        binding.gotoSingUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            loginViewModel.loginState.collect { loginState ->
                loginState?.accessToken?.let { tokenManager.saveAccessToken(it) }
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }
    }

    private fun setPwVisibility() {
        binding.showPwBtn.setOnClickListener {
            loginViewModel.togglePwVisible()

            val selection = binding.inputPw.selectionStart

            if(loginViewModel.isPwVisible.value) {
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