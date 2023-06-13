package com.example.basket.domain.entity

data class BasketDish(
    val id:Int,
    val name:String,
    val price:Int,
    val weight:Int,
    val image:String,
    val count:Int
)