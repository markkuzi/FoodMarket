package com.example.categories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.categories.domain.DishesUseCase
import com.example.categories.domain.InsertBasketUseCase
import com.example.categories.domain.SetupToolbarUseCase
import com.example.categories.domain.entity.BasketDish
import com.example.categories.domain.entity.Dishes
import com.example.core.ResponseResult
import com.example.core.ViewState
import com.example.toolbar.domain.entity.ToolbarSettings
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val dishesUseCase: DishesUseCase,
    private val insertBasketUseCase: InsertBasketUseCase,
    private val setupToolbarUseCase: SetupToolbarUseCase
) : ViewModel() {

    private var _allDishes = MutableLiveData<List<DishesUi>>()
    val allDishes: LiveData<List<DishesUi>>
        get() = _allDishes

    private var _tags = MutableLiveData<List<DishesSortUi>>()
    val tags: LiveData<List<DishesSortUi>>
        get() = _tags

    private var _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    private var tagsList = mutableListOf<DishesSortUi>()
    private var cacheDishesList = mutableListOf<DishesUi>()

    init {
        getDishes()
    }

    fun getDishes() {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading()
            val list = dishesUseCase.getDishes()
            when (list) {
                is ResponseResult.Error -> _viewState.value = ViewState.Error(list.message)
                is ResponseResult.Success -> list.data?.let { setupSuccess(it) }
            }
        }
    }

    private fun setupSuccess(list: List<Dishes>) {
        _viewState.value = ViewState.Success()
        list.let {
            cacheDishesList = it.map { dish ->
                DishesUi(
                    id = dish.id,
                    name = dish.name,
                    price = dish.price,
                    weight = dish.weight,
                    description = dish.description,
                    imageUrl = dish.imageUrl,
                    tegs = dish.tegs.toList()
                )
            }.toMutableList()
        }
        _allDishes.value = cacheDishesList
        getTagsList(cacheDishesList)
        _tags.value = tagsList
    }

    private fun getTagsList(dishesUi: List<DishesUi>) {
        var count = 0
        dishesUi.forEach { dish ->
            val list = dish.tegs.map {
                if (count > 0) {
                    count++
                    DishesSortUi(it, false)
                } else {
                    count++
                    DishesSortUi(it, true)
                }
            }
            list.forEach {
                val tegsName = tagsList.map { it.name }
                if (it.name !in tegsName)
                    tagsList.add(it)
            }
        }
    }

    fun changeTagsSelected(dishesSortUi: DishesSortUi) {
        val newList = mutableListOf<DishesSortUi>()
        if (dishesSortUi.isSelected) {
            tagsList.forEach {
                newList.add(DishesSortUi(it.name, false))
            }
            _tags.value = newList.toList()
            tagsList = newList
            _allDishes.value = emptyList()
        } else {
            tagsList.forEach {
                if (dishesSortUi.name == it.name)
                    newList.add(DishesSortUi(it.name, true))
                else
                    newList.add(DishesSortUi(it.name, false))
            }
            _tags.value = newList.toList()
            tagsList = newList
            changeDishesList(dishesSortUi.name)
        }

    }

    private fun changeDishesList(dish: String) {
        val newList = cacheDishesList.filter {
            dish in it.tegs
        }.toMutableList()
        _allDishes.value = newList
    }

    fun insertDishToBasket(basketDish: BasketDish) {
        viewModelScope.launch {
            insertBasketUseCase.insertDishToBasket(basketDish)
        }

    }

    fun setupCategoriesToolbar(toolbarSettings: ToolbarSettings) {
        viewModelScope.launch {
            setupToolbarUseCase.setupToolbar(toolbarSettings)
        }
    }
}