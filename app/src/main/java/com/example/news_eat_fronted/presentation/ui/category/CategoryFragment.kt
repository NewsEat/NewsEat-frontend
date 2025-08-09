package com.example.news_eat_fronted.presentation.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentCategoryBinding
import com.example.news_eat_fronted.databinding.FragmentHomeBinding
import com.example.news_eat_fronted.util.base.BindingFragment
import kotlinx.coroutines.launch

class CategoryFragment: BindingFragment<FragmentCategoryBinding>(R.layout.fragment_category) {
    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var categoryAdapter: RVAdapterCategoryTab
    private lateinit var newListAdapter: RVAdapterNewsList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        collectData()
//
//        binding.btnMoreNews.visibility = View.GONE
    }

    private fun setAdapter() {
        categoryAdapter = RVAdapterCategoryTab(categoryViewModel.categoryList)
        newListAdapter = RVAdapterNewsList()

        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        binding.rvNewsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newListAdapter
        }
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                categoryViewModel.newsList.collect { newsList ->
                    newListAdapter.submitList(newsList)
                }
            }
        }
    }
}