package com.example.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.home.databinding.CategoryItemBinding
import com.example.home.presentation.CategoryUi
import com.squareup.picasso.Picasso

class CategoryListAdapter():
    ListAdapter<CategoryUi, CategoryViewHolder>(CategoryItemDiffCallback()) {

    var onCategoryItemClickListener: ((category: CategoryUi) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category =getItem(position)

        with(holder.binding) {
            Picasso.get().load(category.imageUrl).into(image)
            text.text = category.name

            root.setOnClickListener {
                onCategoryItemClickListener?.invoke(category)
            }
        }
    }
}