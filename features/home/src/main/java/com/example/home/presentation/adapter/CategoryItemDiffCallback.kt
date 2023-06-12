package com.example.home.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.home.presentation.CategoryUi

class CategoryItemDiffCallback : DiffUtil.ItemCallback<CategoryUi>() {
    override fun areItemsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
        return oldItem == newItem
    }
}
