package com.example.basket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.basket.BasketDishUi
import com.example.basket.databinding.BasketItemBinding

class BasketListAdapter() :
    ListAdapter<BasketDishUi, BasketViewHolder>(BasketItemDiffCallback()) {

    var onItemClickListener: ((basketDish: BasketDishUi) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = BasketItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketDish = getItem(position)

        with(holder.binding) {

        }
    }
}