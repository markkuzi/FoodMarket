package com.example.basket.domain

import com.example.basket.domain.entity.BasketDish

class InsertToBasketUseCase(
    private val basketRepository: BasketRepository
) {
    suspend fun insertToBasket(basketDish: BasketDish) {
        basketRepository.insertToBasket(basketDish)
    }
}