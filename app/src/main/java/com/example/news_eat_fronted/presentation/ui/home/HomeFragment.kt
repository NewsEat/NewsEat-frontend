package com.example.news_eat_fronted.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentHomeBinding
import com.example.news_eat_fronted.domain.entity.response.home.NewsItemEntity
import com.example.news_eat_fronted.presentation.ui.news.NewsDetailActivity
import com.example.news_eat_fronted.util.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var recommendAdapter: RVAdapterNews
    private lateinit var category1Adapter: RVAdapterNews
    private lateinit var category2Adapter: RVAdapterNews
    private lateinit var category3Adapter: RVAdapterNews
    private lateinit var positiveAdapter: RVAdapterNews

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        collectData()
        switchDetoxMode()

        homeViewModel.getHomeNewsSections()
        homeViewModel.getLatestNews()
    }

    private fun setAdapter() {
        recommendAdapter = RVAdapterNews(::onNewsItemClick)
        category1Adapter = RVAdapterNews(::onNewsItemClick)
        category2Adapter = RVAdapterNews(::onNewsItemClick)
        category3Adapter = RVAdapterNews(::onNewsItemClick)
        positiveAdapter = RVAdapterNews(::onNewsItemClick)

        binding.rvNewsRecommended.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendAdapter
        }

        binding.rvNewsCategory1.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = category1Adapter
        }

        binding.rvNewsCategory2.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = category2Adapter
        }

        binding.rvNewsCategory3.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = category3Adapter
        }

        binding.rvNewsPositive.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = positiveAdapter
        }

    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                homeViewModel.nickname.collect { nickname ->
                    binding.categoryNewsTitle.text = getString(R.string.home_category_news_title, nickname)
                }
            }

            launch {
                homeViewModel.interests.collect { interestList ->
                    val categoryIVs = listOf(binding.ivCategory1, binding.ivCategory2, binding.ivCategory3)
                    val categoryTVs = listOf(binding.tvCategory1, binding.tvCategory2, binding.tvCategory3)
                    val categoryRVs = listOf(binding.rvNewsCategory1, binding.rvNewsCategory2, binding.rvNewsCategory3)
                    val divers = listOf(binding.diver3, binding.diver4)

                    interestList.forEachIndexed { index, categoryName ->
                        val item = homeViewModel.categoryListMap[categoryName]
                        if (item != null) {
                            categoryIVs[index].apply {
                                visibility = View.VISIBLE
                                setImageResource(item.imgResId)
                            }
                            categoryTVs[index].apply {
                                visibility = View.VISIBLE
                                setText(item.nameResId)
                            }
                            categoryRVs[index].visibility = View.VISIBLE

                            if(index < divers.size) {
                                divers[index].visibility = View.VISIBLE
                            }
                        }
                    }

                    for (i in interestList.size until 3) {
                        categoryIVs[i].visibility = View.GONE
                        categoryTVs[i].visibility = View.GONE
                        categoryRVs[i].visibility = View.GONE

                        if (i - 1 < divers.size && i != 0) {
                            divers[i-1].visibility = View.GONE
                        }
                    }
                }
            }

            launch {
                homeViewModel.setDetoxState
                    .filterNotNull()
                    .collect { setDetoxState ->
                        homeViewModel.getHomeNewsSections()
                        homeViewModel.getLatestNews()
                }
            }

            launch {
                homeViewModel.homeNewsSectionsState.collect { homeNewsSectionsState ->
                    homeNewsSectionsState?.let { state ->
                        binding.switchDetoxMode.isChecked = state.isDetox

                        val prefCategoryNews = state.sections.filter { it.type == "prefCategoryNews" }
                        prefCategoryNews.forEachIndexed { index, section ->
                            when(index) {
                                0 -> category1Adapter.submitList(section.newsList)
                                1 -> category2Adapter.submitList(section.newsList)
                                2 -> category3Adapter.submitList(section.newsList)
                            }
                        }

                        val interest = prefCategoryNews.map { it.title }
                        homeViewModel.setInterest(interest)

                        val positiveNews = state.sections.find { it.type == "positiveNews" }
                        positiveNews?.let { section ->
                            positiveAdapter.submitList(section.newsList)
                        }
                    }
                }
            }

            launch {
                homeViewModel.latestNewsState.collect { latestNewsState ->
                    latestNewsState?.let {
                        recommendAdapter.submitList(latestNewsState.homeNewsResponses)
                    }
                }
            }
        }
    }

    private fun switchDetoxMode() {
        binding.switchDetoxMode.setOnCheckedChangeListener { _, isChecked ->
            if (binding.switchDetoxMode.isPressed) {
                homeViewModel.setDetoxMode(isChecked)
            }
        }
    }

    private fun onNewsItemClick(item: NewsItemEntity) {
        startActivity(Intent(requireContext(), NewsDetailActivity::class.java).apply {
            putExtra("newsId", item.newsId)
        })
    }
}