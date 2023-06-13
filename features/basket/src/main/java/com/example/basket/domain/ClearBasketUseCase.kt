package com.example.basket.domain

class ClearBasketUseCase(
    private val basketRepository: BasketRepository
) {
    suspend fun clearBasket() {
        basketRepository.clearBasket()
    }
}