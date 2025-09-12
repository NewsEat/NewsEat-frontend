package com.example.news_eat_fronted.presentation.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_eat_fronted.databinding.RvCategoryNewsItemBinding
import com.example.news_eat_fronted.domain.entity.response.news.CategoryNewsResponseEntity

class RVAdapterNewsList(
    private val onItemClick: (CategoryNewsResponseEntity) -> Unit
): ListAdapter<CategoryNewsResponseEntity, RVAdapterNewsList.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvCategoryNewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: RvCategoryNewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryNewsResponseEntity) {
            Glide.with(binding.root)
                .load(item.imgUrl)
                .into(binding.newsImage)
            binding.newsTitle.text = item.title
            binding.publisher.text = item.publisher
            binding.date.text = item.publishedAt

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :  DiffUtil.ItemCallback<CategoryNewsResponseEntity>() {
            override fun areContentsTheSame(oldItem: CategoryNewsResponseEntity, newItem: CategoryNewsResponseEntity): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: CategoryNewsResponseEntity, newItem: CategoryNewsResponseEntity): Boolean {
                return oldItem.newsId== newItem.newsId
            }
        }
    }
}