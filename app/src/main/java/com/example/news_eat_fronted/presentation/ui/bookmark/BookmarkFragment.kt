package com.example.news_eat_fronted.presentation.ui.bookmark

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentBookmarkBinding
import com.example.news_eat_fronted.presentation.ui.news.NewsDetailActivity
import com.example.news_eat_fronted.util.CustomSnackBar
import com.example.news_eat_fronted.util.base.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookmarkFragment : BindingFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {

    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private lateinit var adapter: RVAdapterBookmark

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeBookmarkList()
        collectData()

        bookmarkViewModel.getBookmarkList()
    }

    override fun onResume() {
        super.onResume()
        bookmarkViewModel.getBookmarkList(forceRefresh = true) // 화면 복귀 시 갱신
    }

    private fun setAdapter() {
        adapter = RVAdapterBookmark(
            bookmarkList = arrayListOf(),
            onSelectionChanged = { item ->
                bookmarkViewModel.deleteBookmark(item.bookmarkId) // 북마크 취소
            },
            onSelectItem = { item ->
                startActivity(Intent(requireContext(), NewsDetailActivity::class.java).apply {
                    putExtra("getBookmarkDetail", true)
                    putExtra("bookmarkId", item.bookmarkId)
                })
            }
        )
        binding.rvBookmarkedArticles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@BookmarkFragment.adapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisible = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount

                    if(lastVisible == totalItemCount - 1) {
                        bookmarkViewModel.getBookmarkList(isLoadMore = true)
                    }
                }
            })
        }
    }

    private fun observeBookmarkList() {
        lifecycleScope.launch {
            bookmarkViewModel.bookmarkListState.collect { newList ->
                adapter.apply {
                    bookmarkList.clear()
                    bookmarkList.addAll(newList)
                    notifyDataSetChanged()
                }
            }
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            bookmarkViewModel.deleteBookmarkState.collect {
                CustomSnackBar.make(binding.root, getString(R.string.snackbar_bookmark_deleted)).show()
            }
        }
    }
}