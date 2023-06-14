package com.example.basket.domain

import com.example.basket.domain.entity.BasketDish
import kotlinx.coroutines.flow.Flow

class GetBasketUseCase(
    private val basketRepository: BasketRepository
) {
    fun getBasket(): Flow<List<BasketDish>> {
        return basketRepository.getBasket()
    }
}