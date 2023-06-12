package com.example.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.ViewState
import com.example.home.domain.CategoryUseCase
import com.example.home.domain.entity.ResponseResult
import kotlinx.coroutines.launch

class HomeViewModel(
    private val categoryUseCase: CategoryUseCase
) : ViewModel() {

    private var _allCategories = MutableLiveData<List<CategoryUi>>()
    val allCategories: LiveData<List<CategoryUi>>
        get() = _allCategories

    private var _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    init {
        getCategory()
    }

    fun getCategory() {
        viewModelScope.launch {
            _viewState.value = ViewState.Loading()
            val list = categoryUseCase.getCategories()
            when (list) {
                is ResponseResult.Error -> _viewState.value = ViewState.Error(list.message)
                is ResponseResult.Success -> {
                    _viewState.value = ViewState.Success()
                    _allCategories.value = list.data?.map {
                        CategoryUi(id = it.id, name = it.name, imageUrl = it.imageUrl)
                    }
                }
            }
        }
    }
}