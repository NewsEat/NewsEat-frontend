package com.example.news_eat_fronted.presentation.ui.mypage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivityModifyMypageBinding
import com.example.news_eat_fronted.presentation.ui.signup.SignupStep2Fragment
import com.example.news_eat_fronted.presentation.ui.signup.SignupStep3Fragment
import com.example.news_eat_fronted.util.CustomSnackBar
import com.example.news_eat_fronted.util.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ModifyMyPageActivity: BindingActivity<ActivityModifyMypageBinding>(R.layout.activity_modify_mypage) {
    private lateinit var type: String
    private lateinit var currentNickname : String
    private var currentSelectedCategoryIds: ArrayList<Int>? = null
    private val modifyViewModel: ModifyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentNickname = intent.getStringExtra("current_nickname") ?: ""
        type = intent.getStringExtra("fragment_type") ?: "category"
        currentSelectedCategoryIds = intent.getIntegerArrayListExtra("selected_categories")

        setFragment()
        setHeaderTitle()
        addListeners()
        setButtonVisible()
        collectData()
        observeViewModel()
    }

    private fun addListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnModify.setOnClickListener {
            when(type) {
                "tts" -> {
                    val speed = modifyViewModel.currentSpeed.value
                    val pitch = modifyViewModel.currentPitch.value

                    val prefs = this.getSharedPreferences("TTS_PREFS", Context.MODE_PRIVATE)
                    prefs.edit()
                        .putFloat("TTS_SPEED", speed)
                        .putFloat("TTS_PITCH", pitch)
                        .apply()

                    finish()
                }
                "nickname" -> {
                    modifyViewModel.updateNickname()
                }
                "category" -> {
                    modifyViewModel.updateCategory()
                }
                "password" -> {
                    modifyViewModel.modifyPassword()
                }
            }
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            modifyViewModel.isNextBtnEnabled.collect { enabled ->
                binding.btnModify.isEnabled = enabled
            }
        }

        lifecycleScope.launch {
            modifyViewModel.modifyPwState.collect {
                val resultIntent = Intent().apply {
                    putExtra("pwChanged", true)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                modifyViewModel.updateNicknameState.collect { success ->
                    if (success) {
                        val resultIntent = Intent().apply {
                            putExtra("nicknameChanged", true)
                        }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    } else {
                        CustomSnackBar(binding.root, getString(R.string.snackbar_nickname_update_fail)).show()
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                modifyViewModel.updateCategoryState.collect { success ->
                    if (success) {
                        val resultIntent = Intent().apply {
                            putExtra("categoryChanged", true)
                        }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    } else {
                        CustomSnackBar(binding.root, getString(R.string.snackbar_category_update_fail)).show()
                    }
                }
            }
        }
    }

    private fun setFragment() {
        val fragment = when(type){
            "tts" -> SetTTSFragment()
            "nickname" -> ModifyNicknameFragment()
            "userInfo" -> ModifyUserInfoFragment()
            "password" -> ModifyPwFragment()
            "category" -> {
                modifyViewModel.updateSelectedCategory(currentSelectedCategoryIds)
                modifyViewModel.setOriginalCategories(currentSelectedCategoryIds)

                SignupStep3Fragment().apply {
                    arguments = Bundle().apply {
                        putBoolean("isModify", true)
                        putIntegerArrayList("selected_categories", currentSelectedCategoryIds)
                    }

                }
            }
            else -> SignupStep2Fragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_modify, fragment)
            .commit()
    }

    private fun setHeaderTitle() {
        val title = when(type) {
            "tts" -> "TTS 설정"
            "nickname" -> "닉네임 수정"
            "userInfo" -> "회원정보 수정"
            "password" -> "회원정보 수정"
            "category" -> "관심사 수정"
            else -> "회원정보 수정"
        }

        binding.headerTitle.text = title
    }

    private fun setButtonVisible() {
        if(type == "userInfo") {
            binding.btnModify.visibility = View.GONE
        } else {
            binding.btnModify.visibility = View.VISIBLE
        }
    }

    fun changeToPwModify() {
        val fragment = ModifyPwFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_modify, fragment)
            .commit()
        type = "password"
        setButtonVisible()
    }
}