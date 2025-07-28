package com.example.news_eat_fronted.presentation.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivityModifyMypageBinding
import com.example.news_eat_fronted.presentation.ui.signup.SignupStep2Fragment
import com.example.news_eat_fronted.presentation.ui.signup.SignupStep3Fragment
import com.example.news_eat_fronted.util.base.BindingActivity
import kotlinx.coroutines.launch

class ModifyMyPageActivity: BindingActivity<ActivityModifyMypageBinding>(R.layout.activity_modify_mypage) {
    private lateinit var type: String
    private val modifyViewModel by viewModels<ModifyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        type = intent.getStringExtra("fragment_type") ?: "category"

        setFragment()
        setHeaderTitle()
        addListeners()
        setButtonVisible()
        collectData()
    }

    private fun addListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            modifyViewModel.isNextBtnEnabled.collect { enabled ->
                binding.btnModify.isEnabled = enabled
            }
        }
    }

    private fun setFragment() {
        val fragment = when(type){
            "nickname" -> SignupStep2Fragment().apply {
                arguments = Bundle().apply { putBoolean("isModify", true) }
            }
            "userInfo" -> ModifyUserInfoFragment()
            "password" -> ModifyPwFragment()
            "category" -> SignupStep3Fragment().apply {
                arguments = Bundle().apply { putBoolean("isModify", true) }
            }
            else -> SignupStep2Fragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_modify, fragment)
            .commit()
    }

    private fun setHeaderTitle() {
        val title = when(type) {
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