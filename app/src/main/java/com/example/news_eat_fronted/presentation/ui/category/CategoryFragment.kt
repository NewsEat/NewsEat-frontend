package com.example.news_eat_fronted.presentation.ui.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentCategoryBinding
import com.example.news_eat_fronted.presentation.ui.news.NewsDetailActivity
import com.example.news_eat_fronted.presentation.ui.search.SearchActivity
import com.example.news_eat_fronted.util.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment: BindingFragment<FragmentCategoryBinding>(R.layout.fragment_category) {
    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var categoryAdapter: RVAdapterCategoryTab
    private lateinit var newListAdapter: RVAdapterNewsList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        collectData()
        addListeners()

        categoryViewModel.getCategoryNews(isLoadMore = false)
    }

    private fun addListeners() {
        binding.btnSearch.setOnClickListener {
            startActivity(Intent(requireActivity(), SearchActivity::class.java))
        }
    }

    private fun setAdapter() {
        categoryAdapter = RVAdapterCategoryTab(
            categoryList = categoryViewModel.categoryList,
            onItemSelected = { position -> categoryViewModel.updateSelectedPosition(position) }
        )
        newListAdapter = RVAdapterNewsList(
            onItemClick = { categoryNewsResponseEntity ->
                startActivity(Intent(requireContext(), NewsDetailActivity::class.java).apply {
                    putExtra("newsId", categoryNewsResponseEntity.newsId)
                })
            }
        )

        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }

        binding.rvNewsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newListAdapter

            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisible = layoutManager.findLastCompletelyVisibleItemPosition()
                    val totalCount = layoutManager.itemCount

                    if(lastVisible == totalCount - 1) {
                        categoryViewModel.getCategoryNews(isLoadMore = true)
                    }
                }
            })
        }
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            categoryViewModel.selectedPosition.collect { position ->
                categoryAdapter.updateSelectedPosition(position)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            categoryViewModel.newsList.collect { newsList ->
                newListAdapter.submitList(newsList)
            }
        }
    }
}