package com.example.basket.presentation.adapter

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.basket.R
import com.example.basket.presentation.BasketDishUi
import com.example.basket.databinding.BasketItemBinding
import com.example.basket.domain.entity.BasketDish
import com.squareup.picasso.Picasso

class BasketListAdapter(context: Context) :
    ListAdapter<BasketDish, BasketViewHolder>(BasketItemDiffCallback()) {

    private val mContext = context
    var onUpCountClickListener: ((basketDish: BasketDish) -> Unit)? = null
    var onDownCountClickListener: ((basketDish: BasketDish) -> Unit)? = null

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

            dishBasketName.text = basketDish.name
            dishBasketPrice.text = String.format(mContext.getString(R.string.price),
                basketDish.price.toString())
            dishBasketWeigh.text = String.format(mContext.getString(R.string.weight),
                basketDish.weight.toString())

            dishBasketCount.text = basketDish.count.toString()

            dishCountUp.setOnClickListener {
                onUpCountClickListener?.invoke(basketDish)
            }

            dishCountDown.setOnClickListener {
                onDownCountClickListener?.invoke(basketDish)
            }


            if (basketDish.image.isNotBlank()) {
                Picasso.get().load(basketDish.image).into(dishBasketImage)
            }
            else
                Picasso.get().load("null").into(dishBasketImage)

        }
    }
}