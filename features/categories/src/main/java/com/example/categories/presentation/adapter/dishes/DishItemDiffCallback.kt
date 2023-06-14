package com.example.categories.presentation.adapter.dishes

import androidx.recyclerview.widget.DiffUtil
import com.example.categories.presentation.DishesUi

class DishItemDiffCallback : DiffUtil.ItemCallback<DishesUi>() {
    override fun areItemsTheSame(oldItem: DishesUi, newItem: DishesUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DishesUi, newItem: DishesUi): Boolean {
        return oldItem == newItem
    }
}
