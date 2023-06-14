package com.example.foodmarket.di.modules

import com.example.data.MainRepositoryImpl
import com.example.data.toolbar.MainSetupToolbar
import com.example.data.toolbar.SetupToolbar
import com.example.foodmarket.presentation.MainViewModel
import com.example.toolbar.domain.MainRepository
import com.example.toolbar.domain.SetupToolBarUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single<SetupToolbar> {
        MainSetupToolbar()
    }

    single<MainRepository> {
        MainRepositoryImpl(
            setupToolbar = get()
        )
    }

    factory<SetupToolBarUseCase> {
        SetupToolBarUseCase(mainRepository = get())
    }

    viewModel {
        MainViewModel(
            setupToolBarUseCase = get()
        )
    }
}