package com.example.news_eat_fronted.presentation.ui.mypage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
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
    private lateinit var currentNickname : String;
    private val myPageViewModel: MyPageViewModel by viewModels()
    private val modifyViewModel: ModifyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentNickname = intent.getStringExtra("current_nickname") ?: ""
        type = intent.getStringExtra("fragment_type") ?: "category"

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
                "nickname" -> {
                    val newNickname = modifyViewModel.nickname.value
                    if(newNickname.isNotEmpty()) {
                        myPageViewModel.updateNickname(newNickname)
                    } else {
                        CustomSnackBar.make(binding.root, R.string.snackbar_nickname.toString()).show()
                    }
                }
                "category" -> {
                    val selectedIds = modifyViewModel.selectedCategory.value
                    if(selectedIds.isNotEmpty()) {
                        myPageViewModel.updateCategory(selectedIds)
                    } else {
                        CustomSnackBar.make(binding.root, R.string.snackbar_category_unselected.toString()).show()
                    }
                }
                "password" -> {
                    // 비밀번호 수정 로직
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
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                myPageViewModel.updateNicknameState.collect { success ->
                    val message = if (success) {
                        getString(R.string.snackbar_nickname_update_success)
                    } else {
                        getString(R.string.snackbar_nickname_update_fail)
                    }
                    CustomSnackBar.make(binding.root, message).show()

                    if (success) finish()
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                myPageViewModel.updateCategoryState.collect { success ->
                    val message = if (success) {
                        getString(R.string.snackbar_category_update_success)
                    } else {
                        getString(R.string.snackbar_category_update_fail)
                    }

                    CustomSnackBar.make(binding.root, message).show()

                    if(success) finish()
                }
            }
        }
    }

    private fun setFragment() {
        val fragment = when(type){
            "nickname" -> ModifyNicknameFragment()
//            "nickname" -> SignupStep2Fragment().apply {
//                arguments = Bundle().apply { putBoolean("isModify", true) }
//            }
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