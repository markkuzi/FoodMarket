package com.example.foodmarket.di.modules

import com.example.data.CategoryRepositoryImpl
import com.example.home.domain.CategoryRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryHomeModule = module {

    single<CategoryRepository> {
        CategoryRepositoryImpl(service = get())
    }
}

val viewModelHomeModule = module {

    viewModel{
        (
            repository = get()
        )
    }

}