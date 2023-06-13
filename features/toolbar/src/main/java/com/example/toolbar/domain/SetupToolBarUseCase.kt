package com.example.toolbar.domain

import com.example.toolbar.domain.entity.ToolbarSettings
import kotlinx.coroutines.flow.Flow

class SetupToolBarUseCase(
    private val mainRepository: MainRepository
) {
    fun getSetupToolbar() : Flow<ToolbarSettings> {
        return mainRepository.getSetupToolbar()
    }
}