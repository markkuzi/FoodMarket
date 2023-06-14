package com.example.data.toolbar

import com.example.toolbar.domain.entity.ToolbarSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainSetupToolbar : SetupToolbar {

    private val setupToolbar = MutableStateFlow(
        ToolbarSettings(
            "",
            false
        )
    )

    override fun setupToolbar(toolbar: ToolbarSettings) {
        setupToolbar.value = toolbar
    }

    override fun getSetupToolbar(): Flow<ToolbarSettings> {
        return setupToolbar.asStateFlow()
    }


}