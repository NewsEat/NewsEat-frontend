package com.example.news_eat_fronted.presentation.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentModifyPwBinding
import com.example.news_eat_fronted.util.base.BindingFragment
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ModifyPwFragment: BindingFragment<FragmentModifyPwBinding>(R.layout.fragment_modify_pw) {
    private val modifyViewModel by activityViewModels<ModifyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListeners()
        collectData()
    }

    private fun addListeners() {
        binding.inputPw.addTextChangedListener {
            modifyViewModel.onPwChanged(it.toString())
        }

        binding.inputPwConfirm.addTextChangedListener {
            modifyViewModel.onPwConfirmChanged(it.toString())
        }
    }

    private fun collectData() {
        // 비밀번호 정규식 확인
        lifecycleScope.launch {
            combine(modifyViewModel.pw, modifyViewModel.isPwValid) { pw, isValid ->
                pw to isValid
            }.collect { (pw, isValid) ->
                if (pw.isNotEmpty()) {
                    binding.infoPwError1.visibility = if (isValid) View.GONE else View.VISIBLE
                } else {
                    binding.infoPwError1.visibility = View.GONE
                }
            }
        }
        // 비밀번호 동일입력 확인
        lifecycleScope.launch {
            combine(modifyViewModel.pw,
                modifyViewModel.pwConfirm,
                modifyViewModel.isPwConfirmValid) { pw, pwConfirm, isPwConfirmValid ->
                Triple(pw, pwConfirm, isPwConfirmValid)
            }.collect{ (pw, pwConfirm, isPwConfirmValid) ->
                if(pw.isNotEmpty() && pwConfirm.isNotEmpty()) {
                    binding.infoPwError2.visibility = if (isPwConfirmValid) View.GONE else View.VISIBLE
                } else {
                    binding.infoPwError2.visibility = View.GONE
                }
            }
        }
    }
}