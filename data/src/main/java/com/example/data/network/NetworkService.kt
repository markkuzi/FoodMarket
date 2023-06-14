package com.example.data.network

import retrofit2.http.GET

interface NetworkService {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): CategoryResponse

    @GET("c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishes(): DishesResponse

}