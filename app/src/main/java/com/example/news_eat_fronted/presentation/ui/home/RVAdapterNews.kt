package com.example.news_eat_fronted.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_eat_fronted.databinding.RvHomeNewsItemBinding
import com.example.news_eat_fronted.domain.entity.response.home.NewsItemEntity
import com.example.news_eat_fronted.presentation.model.HomeNewsItem

class RVAdapterNews: ListAdapter<NewsItemEntity, RVAdapterNews.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterNews.ViewHolder {
        val binding = RvHomeNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RVAdapterNews.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: RvHomeNewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsItemEntity) {
            Glide.with(binding.root)
//                .load(binding.root.context.resources.getIdentifier(item.imgResId, "drawable", binding.root.context.packageName))
                .load(item.imgUrl)
                .into(binding.ivHomeNews)
            binding.tvHomeNews.text = item.title
        }
    }

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<NewsItemEntity>() {
            override fun areItemsTheSame(oldItem: NewsItemEntity, newItem: NewsItemEntity): Boolean {
                return oldItem.newsId == newItem.newsId
            }

            override fun areContentsTheSame(oldItem: NewsItemEntity, newItem: NewsItemEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
}