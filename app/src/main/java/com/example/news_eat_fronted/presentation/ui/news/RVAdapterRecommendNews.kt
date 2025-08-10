package com.example.news_eat_fronted.presentation.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.news_eat_fronted.databinding.RvRecommendNewsItemBinding
import com.example.news_eat_fronted.presentation.model.NewsDetailItem

class RVAdapterRecommendNews(
    private val onClickItem: ((NewsDetailItem) -> Unit)? = null
) : ListAdapter<NewsDetailItem, RVAdapterRecommendNews.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterRecommendNews.ViewHolder {
        val binding = RvRecommendNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RVAdapterRecommendNews.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: RvRecommendNewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsDetailItem) {
            Glide.with(binding.root)
//                .load(binding.root.context.resources.getIdentifier(item.imgResId, "drawable", binding.root.context.packageName))
                .load(item.imgResId)
                .into(binding.ivHomeNews)
            binding.tvHomeNews.text = item.title

            binding.root.setOnClickListener {
                onClickItem?.invoke(item)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<NewsDetailItem>() {
            override fun areItemsTheSame(oldItem: NewsDetailItem, newItem: NewsDetailItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewsDetailItem, newItem: NewsDetailItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}