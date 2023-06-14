package com.example.categories.presentation.adapter.chips

import androidx.recyclerview.widget.DiffUtil
import com.example.categories.presentation.DishesSortUi

class ChipItemDiffCallback : DiffUtil.ItemCallback<DishesSortUi>() {
    override fun areItemsTheSame(oldItem: DishesSortUi, newItem: DishesSortUi): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: DishesSortUi, newItem: DishesSortUi): Boolean {
        return oldItem == newItem
    }
}
