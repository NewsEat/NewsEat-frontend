package com.example.news_eat_fronted.presentation.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivitySearchBinding
import com.example.news_eat_fronted.presentation.ui.category.RVAdapterNewsList
import com.example.news_eat_fronted.presentation.ui.news.NewsDetailActivity
import com.example.news_eat_fronted.util.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
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

        binding.inputSearch.addTextChangedListener {
            searchViewModel.onChangeKeyword(it.toString())
        }

        binding.btnSearch.setOnClickListener {
            // 검색 API 호출 후 searchViewModel.newsList 연결
            searchViewModel.getSearchedNews()
            binding.rvSearchedNews.visibility = View.VISIBLE
//            binding.tvNoContent.visibility = View.VISIBLE
        }
    }

    private fun setAdapter() {
        newListAdapter = RVAdapterNewsList(
            onItemClick = { categoryNewsResponseEntity ->
                startActivity(Intent(this, NewsDetailActivity::class.java).apply {
                    putExtra("newsId", categoryNewsResponseEntity.newsId)
                })
            }
        )

        binding.rvSearchedNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newListAdapter
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            searchViewModel.getSearchedNewsState.collect { getSearchedNewsState ->
                newListAdapter.submitList(getSearchedNewsState?.categoryNewsResponses)

            }
        }
    }
}