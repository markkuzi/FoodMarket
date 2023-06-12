package com.example.categories.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.categories.databinding.DishItemBinding
import com.example.categories.presentation.DishesUi
import com.squareup.picasso.Picasso

class DishListAdapter() :
    ListAdapter<DishesUi, DishViewHolder>(DishItemDiffCallback()) {

    var onDishItemClickListener: ((dish: DishesUi) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = DishItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = getItem(position)

        with(holder.binding) {
            Picasso.get().load(dish.imageUrl).into(dishImage)
            dishName.text = dish.name

            root.setOnClickListener {
                onDishItemClickListener?.invoke(dish)
            }
        }
    }
}