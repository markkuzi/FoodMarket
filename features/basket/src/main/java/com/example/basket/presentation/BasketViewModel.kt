package com.example.basket.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.basket.domain.ClearBasketUseCase
import com.example.basket.domain.DeleteFromBasketById
import com.example.basket.domain.GetBasketUseCase
import com.example.basket.domain.InsertToBasketUseCase
import com.example.basket.domain.entity.BasketDish
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class BasketViewModel(
    private val getBasketUseCase: GetBasketUseCase,
    private val insertToBasketUseCase: InsertToBasketUseCase,
    private val deleteFromBasketById: DeleteFromBasketById,
    private val clearBasketUseCase: ClearBasketUseCase
) : ViewModel() {

    val basketList = getBasketUseCase.getBasket().asLiveData()


    fun upDishBasketCount(basketDish: BasketDish) {
        viewModelScope.launch {
            if (basketDish.count != 99){
                val newBasket = basketDish.copy(
                    price = (basketDish.price/basketDish.count) * (basketDish.count + 1),
                    weight = (basketDish.weight/basketDish.count) * (basketDish.count + 1),
                    count = basketDish.count + 1
                )
                insertToBasketUseCase.insertToBasket(newBasket)
            }
        }
    }

    fun downDishBasketCount(basketDish: BasketDish) {
        viewModelScope.launch {
            val newBasket = basketDish.copy(
                price = (basketDish.price/basketDish.count) * (basketDish.count - 1),
                weight = (basketDish.weight/basketDish.count) * (basketDish.count - 1),
                count = basketDish.count - 1
            )
            if (newBasket.count == 0)
                deleteFromBasketById.deleteFromBasketById(basketDish.id)
            else
                insertToBasketUseCase.insertToBasket(newBasket)
        }
    }

    fun clearBasket() {
        viewModelScope.launch {
            clearBasketUseCase.clearBasket()
        }
    }
}