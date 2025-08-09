package com.example.news_eat_fronted.presentation.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_eat_fronted.databinding.RvCategoryNewsItemBinding
import com.example.news_eat_fronted.presentation.model.HomeNewsItem

class RVAdapterNewsList: ListAdapter<HomeNewsItem, RVAdapterNewsList.ViewHolder>(DIFF_CALLBACK) {

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
        fun bind(item: HomeNewsItem) {
            Glide.with(binding.root)
                .load(item.imgResId)
                .into(binding.newsImage)
            binding.newsTitle.text = item.title
            binding.publisher.text = item.publisher
            binding.date.text = item.date
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :  DiffUtil.ItemCallback<HomeNewsItem>() {
            override fun areContentsTheSame(oldItem: HomeNewsItem, newItem: HomeNewsItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: HomeNewsItem, newItem: HomeNewsItem): Boolean {
                return oldItem.id== newItem.id
            }
        }
    }
}