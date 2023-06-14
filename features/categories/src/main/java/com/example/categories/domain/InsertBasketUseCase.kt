package com.example.categories.domain

import com.example.categories.domain.entity.BasketDish
import com.example.categories.domain.entity.Dishes
import com.example.core.ResponseResult

class InsertBasketUseCase(
    private val dishesRepository: DishesRepository
) {
    suspend fun insertDishToBasket(dish: BasketDish) {
        dishesRepository.insertDishToBasket(dish)
    }
}