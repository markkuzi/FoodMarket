package com.example.categories.presentation

data class DishesUi(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val imageUrl: String,
    val tegs: List<String>
)
