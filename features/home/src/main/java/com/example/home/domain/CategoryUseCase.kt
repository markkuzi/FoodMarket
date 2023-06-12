package com.example.home.domain

import com.example.home.domain.entity.Category

class CategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    suspend fun getCategories(): List<Category> {
        return categoryRepository.getCategories()
    }
}