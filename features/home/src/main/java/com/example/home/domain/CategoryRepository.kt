package com.example.home.domain

import com.example.home.domain.entity.Category

interface CategoryRepository {

    suspend fun getCategories() : List<Category>

}