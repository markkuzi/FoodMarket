package com.example.data

import com.example.categories.domain.DishesRepository
import com.example.categories.domain.entity.Dishes
import com.example.core.ResponseResult
import com.example.data.network.NetworkService
import com.example.home.domain.entity.Category

class DishesRepositoryImpl(
    private val service: NetworkService
):DishesRepository {
    override suspend fun getDishes(): ResponseResult<List<Dishes>> {
        try {
            val response = service.getDishes()
            val list = response.dishes.map {
                Dishes(
                    id = it.id,
                    name = it.name,
                    price = it.price,
                    weight = it.weight,
                    description = it.description,
                    imageUrl = it.imageUrl,
                    tegs = it.tegs,
                )
            }
            return ResponseResult.Success(list)
        }
        catch (e: Exception) {
            return ResponseResult.Error(e.message)
        }
    }
}

