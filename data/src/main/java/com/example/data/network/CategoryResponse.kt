package com.example.data.network

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("сategories") var сategories: List<CategoryDto>
)