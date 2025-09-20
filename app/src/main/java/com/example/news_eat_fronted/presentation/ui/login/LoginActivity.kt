package com.example.news_eat_fronted.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
        showSessionExpired()
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
        }

        binding.gotoSingUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            loginViewModel.loginState.collect { loginState ->
                loginState?.let { state ->
                    state.accessToken?.let { accessToken ->
                        tokenManager.saveAccessToken(accessToken)
                    }
                    state.refreshToken?.let { refreshToken ->
                        tokenManager.saveRefreshToken(refreshToken)
                    }

                    if(!state.accessToken.isNullOrEmpty() && !state.refreshToken.isNullOrEmpty()) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginError.collect {
                    CustomSnackBar.make(binding.root, getString(R.string.snackbar_login_error)).show()
                }
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

    private fun showSessionExpired() {
        if(intent.getBooleanExtra("EXTRA_SESSION_EXPIRED", false)) {
            CustomSnackBar(binding.root, getString(R.string.snackbar_session_expired)).show()
        }
    }
}