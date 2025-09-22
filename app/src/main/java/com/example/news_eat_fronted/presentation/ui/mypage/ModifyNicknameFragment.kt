package com.example.news_eat_fronted.presentation.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentModifyNicknameBinding
import com.example.news_eat_fronted.util.base.BindingFragment
import com.example.news_eat_fronted.util.setupKeyboardHide
import kotlinx.coroutines.launch

class ModifyNicknameFragment : BindingFragment<FragmentModifyNicknameBinding>(R.layout.fragment_modify_nickname) {

    private val modifyViewModel by activityViewModels<ModifyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 현재 닉네임 받아오기
        val currentNickname = (activity as? ModifyMyPageActivity)
            ?.intent?.getStringExtra("current_nickname") ?: ""

        binding.inputNickname.setText(currentNickname)
        modifyViewModel.setOriginalNickname(currentNickname)
        modifyViewModel.onNicknameChanged(currentNickname)

        collectData()
        addListeners()
        setupKeyboardHide()
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            modifyViewModel.nicknameLength.collect { length ->
                binding.nicknameLength.text = "$length/10"
            }
        }
    }

    private fun addListeners() {
        binding.inputNickname.addTextChangedListener { text ->
            modifyViewModel.onNicknameChanged(text.toString())
        }
    }
}