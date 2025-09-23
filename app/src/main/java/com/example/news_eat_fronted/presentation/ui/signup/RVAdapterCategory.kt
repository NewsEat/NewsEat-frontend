package com.example.news_eat_fronted.presentation.ui.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.RvCategoryItemBinding
import com.example.news_eat_fronted.presentation.model.CategoryItem
import com.example.news_eat_fronted.util.CustomSnackBar

class RVAdapterCategory(
    val categoryList: ArrayList<CategoryItem>,
    private val preselectedIds: List<Int> = emptyList(),
    private val onSelectionChanged: (List<Int>) -> Unit
    ): RecyclerView.Adapter<RVAdapterCategory.ViewHolder>() {

    init {
        categoryList.forEach { category ->
            category.isSelected = preselectedIds.contains(category.id)
        }
        // 초기 선택된 항목들을 콜백으로 전달
        val initialSelected = categoryList
            .filter { it.isSelected == true }
            .map { it.id }
        if (initialSelected.isNotEmpty()) {
            onSelectionChanged(initialSelected)
        }
    }

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

            // 색상 업데이트를 별도 메서드로 분리
            updateItemAppearance(category)


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
                        CustomSnackBar.make(binding.root, itemView.context.getString(R.string.sanckbar_select_maximum)).show()
                    }
                }


                onSelectionChanged(categoryList
                    .filter { it.isSelected == true }
                    .map { item -> item.id })
            }
        }

        private fun updateItemAppearance(category: CategoryItem) {
            if (category.isSelected == true) {
                val selectedColor = ContextCompat.getColor(itemView.context, R.color.Primary600)
                binding.categoryName.setTextColor(selectedColor)
                binding.categoryImg.setColorFilter(selectedColor)
            } else {
                val unselectedColor = ContextCompat.getColor(itemView.context, R.color.Gray400)
                binding.categoryName.setTextColor(unselectedColor)
                binding.categoryImg.setColorFilter(unselectedColor)
            }
        }
    }
}