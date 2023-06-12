package com.example.foodmarket.di.modules

import com.example.data.network.CategoryService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    factory<Interceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    factory { OkHttpClient.Builder()
        .addInterceptor(interceptor = get())
        .build() }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory<CategoryService>{ get<Retrofit>().create(CategoryService::class.java) }

}