package com.example.news_eat_fronted.presentation.ui.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.RvCategoryItemBinding
import com.example.news_eat_fronted.presentation.model.CategoryItem

class RVAdapterCategory(
    val categoryList: ArrayList<CategoryItem>,
    private val onSelectionChanged: (List<String>) -> Unit
    ): RecyclerView.Adapter<RVAdapterCategory.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RVAdapterCategory.ViewHolder {
        val binding = RvCategoryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RVAdapterCategory.ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class ViewHolder(private val binding: RvCategoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryItem) {
            binding.categoryImg.setImageResource(category.imgResId)
            binding.categoryName.text = itemView.context.getString(category.nameResId)

            if(category.isSelected == true) {
                val color = ContextCompat.getColor(itemView.context, R.color.Primary600)
                binding.categoryName.setTextColor(color)
                binding.categoryImg.setColorFilter(color)
            } else {
                val color = ContextCompat.getColor(itemView.context, R.color.Gray400)
                binding.categoryName.setTextColor(color)
                binding.categoryImg.setColorFilter(color)
            }

            itemView.setOnClickListener {
                val selectedCount = categoryList.count {it.isSelected == true}
                if(category.isSelected == true) {
                    category.isSelected = false
                    notifyItemChanged(adapterPosition)
                } else {
                    if(selectedCount < 3) {
                        category.isSelected = true
                        notifyItemChanged(adapterPosition)
                    }
                    else {
                        // 토스트 메시지 (최대 3개까지 선택가능합니다.)
                    }
                }

                onSelectionChanged(categoryList
                    .filter { it.isSelected == true }
                    .map { item -> itemView.context.getString(item.nameResId) })
            }
        }
    }
}