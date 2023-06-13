package com.example.basket.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basket.BasketDishUi

class BasketItemDiffCallback : DiffUtil.ItemCallback<BasketDishUi>() {
    override fun areItemsTheSame(oldItem: BasketDishUi, newItem: BasketDishUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BasketDishUi, newItem: BasketDishUi): Boolean {
        return oldItem == newItem
    }
}
