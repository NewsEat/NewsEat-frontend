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

        viewModel.setNewsId(intent.getLongExtra("newsId", -1L))
        viewModel.getNewsDetail()

        setupRecyclerView()
        setupBottomSheet()
        collectData()
        setupFloatingButton()
        setupBookmarkButton()
        addListeners()
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

                if(newsDetailState?.isBookmarked == true) {
                    binding.roundBookmarkButton.setImageResource(R.drawable.btn_round_bookmark_selected)
                } else {
                    binding.roundBookmarkButton.setImageResource(R.drawable.btn_round_bookmark_unselected)
                }
            }
        }
    }

    private fun addListeners() {
        binding.summaryButton.setOnClickListener {
            // 요약 API 가져와서 붙여주기
            val dialog = DialogSummaryFragment(
                title = "김도영, 복귀 후 첫 홈런… \nKIA 3연패 탈출 견인",
                content = "김도영바보선수가 복귀 이틀 만에 시즌 첫 홈런을 기록하며 KIA의 3연패를 끊는 데 결정적인 역할을 했습니다. 이날 경기에서 4타수 2안타 2타점을 기록하며 팀 승리에 기여했습니다.",
                tag = getString(R.string.news_summary_positive)
            )
            dialog.show(supportFragmentManager, "DialogSummary")
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
            // 북마크 API 추가

//            isBookmarked = !isBookmarked
//
//            if (isBookmarked) {
//                binding.roundBookmarkButton.setImageResource(R.drawable.btn_round_bookmark_selected)
//            } else {
//                binding.roundBookmarkButton.setImageResource(R.drawable.btn_round_bookmark_unselected)
//            }
        }
    }
}