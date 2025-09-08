package com.example.news_eat_fronted.presentation.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.RvCategoryTabItemBinding

class RVAdapterCategoryTab(
    private val categoryList: ArrayList<String>,
    private var selectedPosition: Int = 0,
    private val onItemSelected: (Int) -> Unit,
): RecyclerView.Adapter<RVAdapterCategoryTab.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterCategoryTab.ViewHolder {
        val binding = RvCategoryTabItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    inner class ViewHolder(private val binding: RvCategoryTabItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryName: String) {
            binding.categoryName.text = categoryName

            if(position == selectedPosition) {
                binding.categoryName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.Primary600))
                binding.categoryIndicator.visibility = View.VISIBLE
            }
            else {
                binding.categoryName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.Primary200))
                binding.categoryIndicator.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                if(selectedPosition != position) {
                    val previousPosition = selectedPosition
                    selectedPosition = position
                    notifyItemChanged(previousPosition)
                    notifyItemChanged(position)

                    onItemSelected(position)
                }
            }
        }
    }

    fun updateSelectedPosition(position: Int) {
        val previous = selectedPosition
        selectedPosition = position
        notifyItemChanged(previous)
        notifyItemChanged(position)
    }
}