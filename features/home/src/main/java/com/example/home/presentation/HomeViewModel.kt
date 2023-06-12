package com.example.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.home.domain.CategoryUseCase

class HomeViewModel(
    private val categoryUseCase: CategoryUseCase
): ViewModel() {

    private var _allCategories = MutableLiveData<CategoryUi>()
    val allCategories: LiveData<CategoryUi>
        get() = _allCategories


}