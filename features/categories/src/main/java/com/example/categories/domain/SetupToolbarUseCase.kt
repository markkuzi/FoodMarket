package com.example.categories.domain

import com.example.toolbar.domain.entity.ToolbarSettings

class SetupToolbarUseCase(
    private val dishesRepository: DishesRepository
) {
    suspend fun setupToolbar(toolbarSettings: ToolbarSettings) {
        dishesRepository.setupToolbar(toolbarSettings)
    }
}