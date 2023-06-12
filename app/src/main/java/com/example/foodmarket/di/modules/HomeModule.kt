package com.example.foodmarket.di.modules

import com.example.data.CategoryRepositoryImpl
import com.example.home.domain.CategoryRepository
import com.example.home.domain.CategoryUseCase
import com.example.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<CategoryRepository> {
        CategoryRepositoryImpl(service = get())
    }

    factory<CategoryUseCase> {
        CategoryUseCase(categoryRepository = get())
    }

    viewModel{
        HomeViewModel(
            categoryUseCase = get()
        )
    }

}