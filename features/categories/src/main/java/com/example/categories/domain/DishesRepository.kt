package com.example.categories.domain

import com.example.categories.domain.entity.BasketDish
import com.example.categories.domain.entity.Dishes
import com.example.core.ResponseResult
import com.example.toolbar.domain.entity.ToolbarSettings

interface DishesRepository {

    suspend fun getDishes(): ResponseResult<List<Dishes>>

    suspend fun insertDishToBasket(basketDish: BasketDish)

    suspend fun setupToolbar(toolbarSettings: ToolbarSettings)

}