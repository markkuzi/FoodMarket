package com.example.data.network

import retrofit2.http.GET

interface CategoryService {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): CategoryResponse

}