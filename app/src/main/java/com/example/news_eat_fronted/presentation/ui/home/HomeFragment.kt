package com.example.news_eat_fronted.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentHomeBinding
import com.example.news_eat_fronted.util.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
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
    }

    private fun setAdapter() {
        recommendAdapter = RVAdapterNews()
        category1Adapter = RVAdapterNews()
        category2Adapter = RVAdapterNews()
        category3Adapter = RVAdapterNews()
        positiveAdapter = RVAdapterNews()

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
                homeViewModel.recommendList.collect { newsList ->
                    recommendAdapter.submitList(newsList)
                }
            }

            launch {
                homeViewModel.category1List.collect { newList ->
                    category1Adapter.submitList(newList)
                }
            }

            launch {
                homeViewModel.category2List.collect { newList ->
                    if(newList.isNullOrEmpty()) {
                        binding.rvNewsCategory2.visibility = View.GONE
                    }
                    else {
                        binding.rvNewsCategory2.visibility = View.VISIBLE
                        category2Adapter.submitList(newList)
                    }
                }
            }

            launch {
                homeViewModel.category3List.collect { newList ->
                    if(newList.isNullOrEmpty()) {
                        binding.rvNewsCategory3.visibility = View.GONE
                    }
                    else {
                        binding.rvNewsCategory3.visibility = View.VISIBLE
                        category3Adapter.submitList(newList)
                    }
                }
            }

            launch {
                homeViewModel.positiveList.collect { newsList ->
                    positiveAdapter.submitList(newsList)
                }
            }
        }
    }
}