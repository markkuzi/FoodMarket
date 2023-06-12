package com.example.categories.domain

import com.example.categories.domain.entity.Dishes
import com.example.core.ResponseResult

class DishesUseCase(
    private val dishesRepository: DishesRepository
) {
    suspend fun getDishes(): ResponseResult<List<Dishes>> {
        return dishesRepository.getDishes()
    }
}