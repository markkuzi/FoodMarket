package com.example.data.network

import com.google.gson.annotations.SerializedName

data class DishesDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("tegs") val tegs: List<String>
)