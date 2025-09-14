package com.example.news_eat_fronted.presentation.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.RvBookmarkItemBinding
import com.example.news_eat_fronted.domain.entity.response.bookmark.BookmarkResponseEntity
import com.example.news_eat_fronted.presentation.model.BookmarkItem

class RVAdapterBookmark (
    val bookmarkList: ArrayList<BookmarkResponseEntity>,
    private val onSelectionChanged: (BookmarkResponseEntity) -> Unit
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
        fun bind(item: BookmarkResponseEntity) {
            binding.bookmarkNewsTitle.text = item.title
            binding.bookmarkNewsCategory.text = item.category
            binding.bookmarkNewsDate.text = item.publishedAt
            binding.bookmarkNewsThumbnail.load(item.imgUrl) {
                crossfade(true)
            }
            binding.iconBookmark.setImageResource(R.drawable.icon_bookmark_selected)

            binding.root.setOnClickListener {
                // 뉴스 단건 조회
            }

            binding.iconBookmark.setOnClickListener {
                onSelectionChanged(item) // 북마크 취소
            }
        }
    }
}