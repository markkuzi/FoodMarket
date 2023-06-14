package com.example.data

import com.example.categories.domain.DishesRepository
import com.example.categories.domain.entity.BasketDish
import com.example.categories.domain.entity.Dishes
import com.example.core.ResponseResult
import com.example.data.localBd.BasketDao
import com.example.data.localBd.BasketModel
import com.example.data.network.NetworkService
import com.example.data.toolbar.SetupToolbar
import com.example.home.domain.entity.Category
import com.example.toolbar.domain.entity.ToolbarSettings

class DishesRepositoryImpl(
    private val service: NetworkService,
    private val basketDao: BasketDao,
    private val setupToolbar: SetupToolbar
) : DishesRepository {
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
                    imageUrl = it.imageUrl ?: "",
                    tegs = it.tegs.toList(),
                )
            }
            return ResponseResult.Success(list)
        } catch (e: Exception) {
            return ResponseResult.Error(e.message)
        }
    }

    override suspend fun insertDishToBasket(basketDish: BasketDish) {
        basketDao.insert(
            BasketModel(
                id = basketDish.id,
                name = basketDish.name,
                price = basketDish.price,
                weight = basketDish.weight,
                image = basketDish.image,
                count = basketDish.count
            )
        )
    }

    override suspend fun setupToolbar(toolbarSettings: ToolbarSettings) {
        setupToolbar.setupToolbar(toolbarSettings)
    }
}
