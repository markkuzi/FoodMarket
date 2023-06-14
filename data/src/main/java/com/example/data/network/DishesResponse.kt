package com.example.data.network

import com.google.gson.annotations.SerializedName

data class DishesResponse(
    @SerializedName("dishes" ) var dishes : List<DishesDto>
)
