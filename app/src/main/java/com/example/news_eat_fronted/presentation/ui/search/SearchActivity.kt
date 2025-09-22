package com.example.news_eat_fronted.presentation.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivitySearchBinding
import com.example.news_eat_fronted.presentation.ui.category.RVAdapterNewsList
import com.example.news_eat_fronted.presentation.ui.news.NewsDetailActivity
import com.example.news_eat_fronted.util.base.BindingActivity
import com.example.news_eat_fronted.util.hideKeyboard
import com.example.news_eat_fronted.util.setupKeyboardHide
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
        setupKeyboardHide()
    }

    private fun addListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.inputSearch.addTextChangedListener {
            searchViewModel.onChangeKeyword(it.toString())
        }

        binding.btnSearch.setOnClickListener {
            it.hideKeyboard()

            searchViewModel.apply {
                resetNewsList()
                getSearchedNews(isLoadMore = false)
            }
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

            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()
                    val totalCount = layoutManager.itemCount

                    if(lastVisible == totalCount - 1) {
                        searchViewModel.getSearchedNews(isLoadMore = true)
                    }
                }
            })
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            searchViewModel.newsList.collect { newsList ->
                newListAdapter.submitList(newsList)

                if(searchViewModel.getSearchedNewsState.value != null) {
                    binding.rvSearchedNews.visibility = if(newsList.isNotEmpty()) View.VISIBLE else View.GONE
                    binding.tvNoContent.visibility = if(newsList.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
    }
}