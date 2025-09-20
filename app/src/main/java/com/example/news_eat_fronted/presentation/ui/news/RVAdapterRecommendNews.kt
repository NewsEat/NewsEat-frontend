package com.example.news_eat_fronted.presentation.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_eat_fronted.databinding.RvRecommendNewsItemBinding
import com.example.news_eat_fronted.domain.entity.response.news.RecommendationsResponseEntity

class RVAdapterRecommendNews(
    private val onClickItem: ((RecommendationsResponseEntity) -> Unit)? = null
) : ListAdapter<RecommendationsResponseEntity, RVAdapterRecommendNews.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterRecommendNews.ViewHolder {
        val binding = RvRecommendNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RVAdapterRecommendNews.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: RvRecommendNewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecommendationsResponseEntity) {
            Glide.with(binding.root)
                .load(item.imgUrl)
                .into(binding.ivHomeNews)
            binding.tvHomeNews.text = item.title

            binding.root.setOnClickListener {
                onClickItem?.invoke(item)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<RecommendationsResponseEntity>() {
            override fun areItemsTheSame(oldItem: RecommendationsResponseEntity, newItem: RecommendationsResponseEntity): Boolean {
                return oldItem.newsId == newItem.newsId
            }

            override fun areContentsTheSame(oldItem: RecommendationsResponseEntity, newItem: RecommendationsResponseEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}