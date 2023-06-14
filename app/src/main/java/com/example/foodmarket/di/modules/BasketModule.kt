package com.example.foodmarket.di.modules

import com.example.basket.domain.BasketRepository
import com.example.basket.domain.ClearBasketUseCase
import com.example.basket.domain.DeleteFromBasketById
import com.example.basket.domain.GetBasketUseCase
import com.example.basket.domain.InsertToBasketUseCase
import com.example.basket.presentation.BasketViewModel
import com.example.categories.presentation.CategoriesViewModel
import com.example.data.BasketRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val basketModule = module {

    single<BasketRepository> {
        BasketRepositoryImpl(
            basketDao = get()
        )
    }

    factory<GetBasketUseCase> {
        GetBasketUseCase(basketRepository = get())
    }

    factory<InsertToBasketUseCase> {
        InsertToBasketUseCase(basketRepository = get())
    }

    factory<DeleteFromBasketById> {
        DeleteFromBasketById(basketRepository = get())
    }

    factory<ClearBasketUseCase> {
        ClearBasketUseCase(basketRepository = get())
    }

    viewModel {
        BasketViewModel(
            getBasketUseCase = get(),
            insertToBasketUseCase = get(),
            deleteFromBasketById = get(),
            clearBasketUseCase = get()
        )
    }
}