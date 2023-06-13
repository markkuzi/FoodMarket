package com.example.toolbar.domain

import com.example.toolbar.domain.entity.ToolbarSettings
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getSetupToolbar(): Flow<ToolbarSettings>
}