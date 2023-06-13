package com.example.basket.domain

class DeleteFromBasketById(
    private val basketRepository: BasketRepository
) {
    suspend fun deleteFromBasketById(id: Int) {
        basketRepository.deleteFromBasketById(id)
    }
}