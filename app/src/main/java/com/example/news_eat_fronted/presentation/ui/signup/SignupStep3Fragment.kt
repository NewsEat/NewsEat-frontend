package com.example.news_eat_fronted.presentation.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentSignupStep3Binding
import com.example.news_eat_fronted.presentation.model.CategoryItem
import com.example.news_eat_fronted.presentation.ui.mypage.ModifyViewModel
import com.example.news_eat_fronted.util.base.BindingFragment

class SignupStep3Fragment: BindingFragment<FragmentSignupStep3Binding>(R.layout.fragment_signup_step3) {
    val categoryList = listOf(
        CategoryItem(R.drawable.category_politics_unselected, R.string.category_politics),
        CategoryItem(R.drawable.category_economy_unselected, R.string.category_economy),
        CategoryItem(R.drawable.category_social_unselected, R.string.category_social),
        CategoryItem(R.drawable.category_culture_unselected, R.string.category_culture),
        CategoryItem(R.drawable.category_science_unselected, R.string.category_science),
        CategoryItem(R.drawable.category_entertainment_unselected, R.string.category_entertainment),
        CategoryItem(R.drawable.category_sports_unselected, R.string.category_sports),
        CategoryItem(R.drawable.category_world_unselected, R.string.category_world),
    )
    private val signupViewModel by activityViewModels<SignupViewModel>()
    private val modifyViewModel by activityViewModels<ModifyViewModel>()
    private var isModify: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isModify = arguments?.getBoolean("isModify") ?: false

        setAdapter()
    }

    private fun setAdapter() {
        val adapter = RVAdapterCategory(categoryList = ArrayList(categoryList)) { selectedList ->
            if(isModify) {
                modifyViewModel.updateSelectedCategory(selectedList)
            } else {
                signupViewModel.updateSelectedCategory(selectedList)
            }
        }
        binding.rvCategory.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            this.adapter = adapter
        }
    }
}