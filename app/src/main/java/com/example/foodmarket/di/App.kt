package com.example.foodmarket.di

import android.app.Application
import com.example.foodmarket.di.modules.categoriesModule
import com.example.foodmarket.di.modules.networkModule
import com.example.foodmarket.di.modules.homeModule
import com.example.foodmarket.di.modules.localBdModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application()  {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(homeModule, networkModule, categoriesModule, localBdModule)
        }
    }
}