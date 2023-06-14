package com.example.basket.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.basket.domain.entity.BasketDish
import com.example.basket.presentation.BasketDishUi

class BasketItemDiffCallback : DiffUtil.ItemCallback<BasketDish>() {
    override fun areItemsTheSame(oldItem: BasketDish, newItem: BasketDish): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BasketDish, newItem: BasketDish): Boolean {
        return oldItem == newItem
    }
}
