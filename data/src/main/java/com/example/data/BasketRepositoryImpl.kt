package com.example.data

import com.example.basket.domain.BasketRepository
import com.example.basket.domain.entity.BasketDish
import com.example.data.localBd.BasketDao
import com.example.data.localBd.BasketModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class BasketRepositoryImpl(
    private val basketDao: BasketDao
) : BasketRepository {

    override fun getBasket(): Flow<List<BasketDish>> {
        val list = basketDao.getBasket()
        return list.transform {
            emit(it.map { basketModel ->
                BasketDish(
                    id = basketModel.id,
                    name = basketModel.name,
                    price = basketModel.price,
                    weight = basketModel.weight,
                    image = basketModel.image,
                    count = basketModel.count
                )
            })
        }
    }

    override suspend fun insertToBasket(basketDish: BasketDish) {
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

    override suspend fun deleteFromBasketById(id: Int) {
        basketDao.deleteDishFromBasket(id)
    }

    override suspend fun clearBasket() {
        basketDao.clear()
    }
}