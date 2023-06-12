package com.example.categories.domain

import com.example.categories.domain.entity.Dishes
import com.example.core.ResponseResult

interface DishesRepository {

    suspend fun getDishes(): ResponseResult<List<Dishes>>

}