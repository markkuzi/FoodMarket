package com.example.data.network

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("image_url") var imageUrl: String
)
