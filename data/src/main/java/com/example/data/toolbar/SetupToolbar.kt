package com.example.data.toolbar

import com.example.toolbar.domain.entity.ToolbarSettings
import kotlinx.coroutines.flow.Flow

interface SetupToolbar {

    fun setupToolbar(toolbar: ToolbarSettings)

    fun getSetupToolbar(): Flow<ToolbarSettings>

}