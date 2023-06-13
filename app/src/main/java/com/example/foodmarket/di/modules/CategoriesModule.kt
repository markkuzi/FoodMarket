package com.example.foodmarket.di.modules

import com.example.categories.domain.DishesRepository
import com.example.categories.domain.DishesUseCase
import com.example.categories.domain.InsertBasketUseCase
import com.example.categories.domain.SetupToolbarUseCase
import com.example.categories.presentation.CategoriesViewModel
import com.example.data.CategoryRepositoryImpl
import com.example.data.DishesRepositoryImpl
import com.example.home.domain.CategoryRepository
import com.example.home.domain.CategoryUseCase
import com.example.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoriesModule = module {

    single<DishesRepository> {
        DishesRepositoryImpl(
            service = get(),
            basketDao = get(),
            setupToolbar = get()
        )
    }

    factory<DishesUseCase> {
        DishesUseCase(dishesRepository = get())
    }

    factory<InsertBasketUseCase> {
        InsertBasketUseCase(dishesRepository = get())
    }

    factory<SetupToolbarUseCase> {
        SetupToolbarUseCase(dishesRepository = get())
    }

    viewModel {
        CategoriesViewModel(
            dishesUseCase = get(),
            insertBasketUseCase = get(),
            setupToolbarUseCase = get()
        )
    }

}