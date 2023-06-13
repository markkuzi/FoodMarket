package com.example.data


import com.example.data.toolbar.SetupToolbar
import com.example.toolbar.domain.MainRepository
import com.example.toolbar.domain.entity.ToolbarSettings
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val setupToolbar: SetupToolbar
): MainRepository {
    override fun getSetupToolbar(): Flow<ToolbarSettings> {
        return setupToolbar.getSetupToolbar()
    }
}