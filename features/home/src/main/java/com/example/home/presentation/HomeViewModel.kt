package com.example.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.domain.CategoryUseCase
import com.example.home.domain.entity.ResponseResult
import kotlinx.coroutines.launch

class HomeViewModel(
    private val categoryUseCase: CategoryUseCase
) : ViewModel() {

    private var _allCategories = MutableLiveData<List<CategoryUi>>()
    val allCategories: LiveData<List<CategoryUi>>
        get() = _allCategories

    init {
        getCategory()
    }

    private fun getCategory() {
        viewModelScope.launch {
            val list = categoryUseCase.getCategories()
            when (list) {
                is ResponseResult.Error -> TODO()
                is ResponseResult.Success -> {
                    _allCategories.value = list.data?.map {
                        CategoryUi(id = it.id, name = it.name, imageUrl = it.imageUrl)
                    }
                }
            }
        }

    }
}