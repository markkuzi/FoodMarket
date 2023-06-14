package com.example.foodmarket.di.modules

import androidx.room.Room
import com.example.data.localBd.BasketBd
import com.example.data.network.NetworkService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

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

    factory<NetworkService>{ get<Retrofit>().create(NetworkService::class.java) }

}

val localBdModule = module {
    single {
        Room.databaseBuilder(androidContext(), BasketBd::class.java,
            "BasketDb").build()
    }

    single { get<BasketBd>().basketDao }
}