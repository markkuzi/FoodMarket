package com.example.categories.presentation.adapter.chips

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.categories.databinding.ChipItemBinding
import com.example.categories.presentation.DishesSortUi

class ChipListAdapter() :
    ListAdapter<DishesSortUi, ChipViewHolder>(ChipItemDiffCallback()) {

    var onChipItemClickListener: ((chip: DishesSortUi) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val binding = ChipItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        val activeChip = getItem(position)

        with(holder.binding) {

            chip.isChecked = activeChip.isSelected

            chip.text = activeChip.name

            chip.setOnClickListener {
                onChipItemClickListener?.invoke(activeChip)
            }
        }
    }
}