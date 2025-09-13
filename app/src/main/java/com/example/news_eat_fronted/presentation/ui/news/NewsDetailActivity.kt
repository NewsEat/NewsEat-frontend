package com.example.news_eat_fronted.presentation.ui.news

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.ActivityNewsDetailBinding
import com.example.news_eat_fronted.util.base.BindingActivity
import com.example.news_eat_fronted.util.dialog.DialogSummaryFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsDetailActivity : BindingActivity<ActivityNewsDetailBinding>(R.layout.activity_news_detail) {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var adapter: RVAdapterRecommendNews

    private val offsetFromTopDp = 250
    private var isTTSActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getNews()
        setupRecyclerView()
        setupBottomSheet()
        collectData()
        setupFloatingButton()
        setupBookmarkButton()
        addListeners()
    }

    private fun getNews() {
        viewModel.setNewsId(intent.getLongExtra("newsId", -1L))

        viewModel.getNewsDetail()
        viewModel.getNewsSummary()
    }

    private fun setupRecyclerView() {
        adapter = RVAdapterRecommendNews { item ->
            // 추천 뉴스 API 붙이기
//            viewModel.selectNews(item)
        }
        binding.rvNewsRecommended.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNewsRecommended.adapter = adapter
    }

    private fun setupBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)

        val density = resources.displayMetrics.density
        val offsetFromTopPx = (offsetFromTopDp * density).toInt()

        // 레이아웃이 그려진 후 높이 계산
        binding.bottomSheet.post {
            val screenHeight = resources.displayMetrics.heightPixels
            val peekHeight = screenHeight - offsetFromTopPx
            bottomSheetBehavior.peekHeight = peekHeight.coerceAtLeast(0)

            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) { }
            override fun onSlide(bottomSheet: View, slideOffset: Float) { }
        })
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.newsDetailState.collect { newsDetailState ->
                Glide.with(this@NewsDetailActivity)
                    .load(newsDetailState?.imgUrl)
                    .into(binding.newsImage)

                binding.newsTitle.text = newsDetailState?.title
                binding.newsContent.text = newsDetailState?.content
                binding.newsPublisher.text = newsDetailState?.publisher
                binding.newsDate.text = newsDetailState?.publishedAt
                binding.newsCategory.text = newsDetailState?.category
                binding.newsSentiment.text = newsDetailState?.sentiment
                newsDetailState?.isBookmarked?.let { viewModel.setBookmarked(it) }
            }
        }

        lifecycleScope.launch {
            viewModel.isBookmarked.collect { isBookmarked ->
                if(isBookmarked) {
                    binding.roundBookmarkButton.setImageResource(R.drawable.btn_round_bookmark_selected)
                } else {
                    binding.roundBookmarkButton.setImageResource(R.drawable.btn_round_bookmark_unselected)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.postBookmarkState.collect { postBookmarkState ->
                viewModel.setBookmarked(true)
                postBookmarkState?.bookmarkId?.let { viewModel.setBookmarkId(it) }
            }
        }
    }

    private fun addListeners() {
        binding.summaryButton.setOnClickListener {
            viewModel.newsSummaryState.value?.let { newsSummaryState ->
                val dialog = DialogSummaryFragment(
                    title = newsSummaryState.title,
                    content = newsSummaryState.summaryResult,
                    tag = newsSummaryState.sentiment
                )
                dialog.show(supportFragmentManager, "DialogSummary")
            }
        }

        binding.roundBackButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupFloatingButton() {
        binding.fabWrite.setOnClickListener {
            isTTSActive = !isTTSActive

            if (isTTSActive) {
                binding.fabWrite.setImageResource(R.drawable.icon_tts_stop)
            } else {
                binding.fabWrite.setImageResource(R.drawable.icon_tts)
            }
        }
    }

    private fun setupBookmarkButton() {
        binding.roundBookmarkButton.setOnClickListener {

            if(!viewModel.isBookmarked.value) {
                viewModel.postBookmark() // 북마크 추가
            } else {
                viewModel.deleteBookmark() // 북마크 삭제
            }
        }
    }
}