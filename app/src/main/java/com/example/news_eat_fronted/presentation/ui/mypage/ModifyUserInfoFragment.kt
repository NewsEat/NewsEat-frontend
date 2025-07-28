package com.example.news_eat_fronted.presentation.ui.mypage

import android.graphics.Paint
import android.os.Bundle
import android.view.View
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentModifyUserInfoBinding
import com.example.news_eat_fronted.util.base.BindingFragment

class ModifyUserInfoFragment: BindingFragment<FragmentModifyUserInfoBinding>(R.layout.fragment_modify_user_info) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPwModify.paintFlags = binding.btnPwModify.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    private fun addListeners() {
        binding.btnPwModify.setOnClickListener {

        }
    }
}