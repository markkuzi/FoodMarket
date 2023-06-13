package com.example.basket.domain

import com.example.basket.domain.entity.BasketDish
import kotlinx.coroutines.flow.Flow

interface BasketRepository {

    fun getBasket(): Flow<List<BasketDish>>

    suspend fun insertToBasket(basketDish: BasketDish)

    suspend fun deleteFromBasketById(id: Int)

    suspend fun clearBasket()
}