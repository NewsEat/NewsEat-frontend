package com.example.news_eat_fronted.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentBookmarkBinding
import com.example.news_eat_fronted.presentation.ui.bookmark.BookmarkViewModel
import com.example.news_eat_fronted.presentation.ui.bookmark.RVAdapterBookmark
import com.example.news_eat_fronted.util.base.BindingFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BookmarkFragment : BindingFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {

    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private lateinit var adapter: RVAdapterBookmark

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeBookmarkList()
    }

    private fun setAdapter() {
        adapter = RVAdapterBookmark(arrayListOf()) { item ->
            bookmarkViewModel.updateBookmarkStatus(item)
        }
        binding.rvBookmarkedArticles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@BookmarkFragment.adapter
        }
    }

    private fun observeBookmarkList() {
        lifecycleScope.launch {
            bookmarkViewModel.bookmarkList.collectLatest { list ->
                adapter.apply {
                    bookmarkList.clear()
                    bookmarkList.addAll(list.filter { it.isBookmarked })
                    notifyDataSetChanged()
                }
            }
        }
    }
}