package com.example.news_eat_fronted.presentation.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.RvBookmarkItemBinding
import com.example.news_eat_fronted.presentation.model.BookmarkItem

class RVAdapterBookmark (
    val bookmarkList: ArrayList<BookmarkItem>,
    private val onSelectionChanged: (BookmarkItem) -> Unit
): RecyclerView.Adapter<RVAdapterBookmark.ViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterBookmark.ViewHolder {
        val binding = RvBookmarkItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bookmarkList[position])
    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    inner class ViewHolder(private val binding: RvBookmarkItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkItem) {
            binding.bookmarkNewsTitle.text = item.title
            binding.bookmarkNewsCategory.text = item.category
            binding.bookmarkNewsDate.text = item.date

            binding.bookmarkNewsThumbnail.load(item.thumbnailUrl) {
                crossfade(true)
            }

            val bookmarkIcon = if (item.isBookmarked) {
                R.drawable.icon_bookmark_selected
            } else {
                R.drawable.icon_bookmark_unselected
            }

            binding.iconBookmark.setImageResource(bookmarkIcon)

            binding.iconBookmark.setOnClickListener {
                onSelectionChanged(item)
            }
        }
    }
}