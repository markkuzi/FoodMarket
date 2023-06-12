package com.example.categories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categories.domain.DishesUseCase
import com.example.core.ResponseResult
import com.example.core.ViewState
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val dishesUseCase: DishesUseCase
): ViewModel() {

    private var _allDishes = MutableLiveData<List<DishesUi>>()
    val allDishes: LiveData<List<DishesUi>>
        get() = _allDishes

    private var _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    init {
        getDishes()
    }

    fun getDishes() {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading()
            val list = dishesUseCase.getDishes()
            when (list) {
                is ResponseResult.Error -> _viewState.value = ViewState.Error(list.message)
                is ResponseResult.Success -> {
                    _viewState.value = ViewState.Success()
                    _allDishes.value = list.data?.map {
                        DishesUi(
                            id = it.id,
                            name = it.name,
                            price = it.price,
                            weight = it.weight,
                            description = it.description,
                            imageUrl = it.imageUrl
                        )
                    }
                }
            }
        }
    }
}