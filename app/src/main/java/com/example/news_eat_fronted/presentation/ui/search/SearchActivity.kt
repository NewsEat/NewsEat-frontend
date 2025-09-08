package com.example.news_eat_fronted.presentation.ui.search

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivitySearchBinding
import com.example.news_eat_fronted.presentation.ui.category.RVAdapterNewsList
import com.example.news_eat_fronted.util.base.BindingActivity
import kotlinx.coroutines.launch

class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var newListAdapter: RVAdapterNewsList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setAdapter()
        collectData()
        addListeners()
    }

    private fun addListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSearch.setOnClickListener {
            // 검색 API 호출 후 searchViewModel.newsList 연결
            binding.rvSearchedNews.visibility = View.VISIBLE
//            binding.tvNoContent.visibility = View.VISIBLE
        }
    }

    private fun setAdapter() {
        newListAdapter = RVAdapterNewsList()

        binding.rvSearchedNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newListAdapter
        }
    }

    private fun collectData() {
//        lifecycleScope.launch {
//            launch {
//                searchViewModel.newsList.collect { newsList ->
//                    newListAdapter.submitList(newsList)
//                }
//            }
//        }
    }
}