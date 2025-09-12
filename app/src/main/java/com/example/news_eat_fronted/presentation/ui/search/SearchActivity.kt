package com.example.news_eat_fronted.presentation.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            binding.rvSearchedNews.visibility = View.GONE
            binding.tvNoContent.visibility = View.GONE
        }

        binding.inputSearch.addTextChangedListener {
            searchViewModel.onChangeKeyword(it.toString())
        }

        binding.btnSearch.setOnClickListener {
            // 검색 API 호출 후 searchViewModel.newsList 연결
            searchViewModel.getSearchedNews()
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
            Log.d("okhttp", "collectData start")
            searchViewModel.getSearchedNewsState.collect { getSearchedNewsState ->
                Log.d("okhttp", "collectData collect triggered: $getSearchedNewsState")
                Log.d("okhttp", getSearchedNewsState.toString())

                newListAdapter.submitList(getSearchedNewsState?.searchNewsResponses)

                if(getSearchedNewsState !== null) {
                    if((getSearchedNewsState?.searchNewsResponses?.size ?: 0) > 0) {
                        binding.rvSearchedNews.visibility = View.VISIBLE
                        binding.tvNoContent.visibility = View.GONE
                    } else {
                        binding.rvSearchedNews.visibility = View.GONE
                        binding.tvNoContent.visibility = View.VISIBLE
                    }
                }
            }

        }
    }
}