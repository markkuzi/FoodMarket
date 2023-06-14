package com.example.foodmarket.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.toolbar.domain.SetupToolBarUseCase

class MainViewModel(
    private val setupToolBarUseCase: SetupToolBarUseCase
) : ViewModel() {

    val setupToolbar = setupToolBarUseCase.getSetupToolbar().asLiveData()

}