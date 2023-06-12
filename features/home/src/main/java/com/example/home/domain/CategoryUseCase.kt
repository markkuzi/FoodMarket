package com.example.home.domain

import com.example.home.domain.entity.Category
import com.example.home.domain.entity.ResponseResult

class CategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend fun getCategories(): ResponseResult<List<Category>> {
        return categoryRepository.getCategories()
    }
}