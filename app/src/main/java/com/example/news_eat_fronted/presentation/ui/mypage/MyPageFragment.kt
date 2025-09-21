package com.example.news_eat_fronted.presentation.ui.mypage

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentHomeBinding
import com.example.news_eat_fronted.databinding.FragmentMypageBinding
import com.example.news_eat_fronted.presentation.ui.login.LoginActivity
import com.example.news_eat_fronted.util.base.BindingFragment
import com.example.news_eat_fronted.util.dialog.DialogPopupFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPageFragment: BindingFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val viewModel: MyPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadProfile()

        collectData()
        addListeners()
    }

    private fun collectData() {

        lifecycleScope.launch {
            viewModel.nickname.collectLatest { nickname ->
                val formattedText = getString(R.string.mypage_member_name, nickname)
                binding.mypageNickname.text = formattedText
            }
        }

        lifecycleScope.launch {
            viewModel.nickname.collectLatest { greeting ->
                val formattedText = getString(R.string.mypage_greeting, greeting)
                binding.mypageGreeting.text = formattedText
            }
        }

        lifecycleScope.launch {
            viewModel.interests.collectLatest { tags ->
                updateInterestTags(tags)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.withdrawState.collect {
                startActivity(
                    Intent(requireContext(), LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                )
            }
        }
    }

    private fun updateInterestTags(tags: List<String>) {
        val container: LinearLayout = binding.interestTags
        container.removeAllViews()

        val marginInPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 7f, resources.displayMetrics
        ).toInt()

        tags.forEach { tag ->
            val tagView = TextView(requireContext()).apply {
                text = tag
                setPadding(16, 8, 16, 8)
                setTextColor(resources.getColor(R.color.Primary500, null))
                setTextAppearance(R.style.TextAppearance_NewsEat_Body11)
                background = GradientDrawable().apply {
                    shape = GradientDrawable.RECTANGLE
                    cornerRadius = 10f
                    setColor(Color.WHITE)
                }
            }

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                marginEnd = marginInPx
            }

            tagView.layoutParams = params
            container.addView(tagView)
        }
    }

    private fun addListeners() {
        binding.btnEditNickname.setOnClickListener {
            startActivity(Intent(requireContext(), ModifyMyPageActivity::class.java).apply {
                putExtra("fragment_type", "nickname")
            })
        }

        binding.menuInterest.setOnClickListener {
            startActivity(Intent(requireContext(), ModifyMyPageActivity::class.java).apply {
                putExtra("fragment_type", "category")
            })
        }

        binding.menuProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ModifyMyPageActivity::class.java).apply {
                putExtra("fragment_type", "userInfo")
            })
        }

        binding.menuLogout.setOnClickListener {
            val dialog = DialogPopupFragment(
                title = getString(R.string.mypage_logout_title),
                content = getString(R.string.mypage_logout_content),
                leftBtnText = getString(R.string.dialog_btn_cancel),
                rightBtnText = getString(R.string.dialog_btn_logout),
                clickLeftBtn = {},
                clickRightBtn = {
                    // 로그아웃 API
                }
            )
            dialog.show(parentFragmentManager, "DialogLogout")
        }

        binding.menuWithdraw.setOnClickListener {
            val dialog = DialogPopupFragment(
                title = getString(R.string.mypage_delete_title),
                content = getString(R.string.mypage_delete_content),
                leftBtnText = getString(R.string.dialog_btn_cancel),
                rightBtnText = getString(R.string.dialog_btn_delete),
                clickLeftBtn = {},
                clickRightBtn = {
                    viewModel.withdraw()
                }
            )
            dialog.show(parentFragmentManager, "DialogLogout")
        }
    }
}