package com.example.home.domain

import com.example.home.domain.entity.Category
import com.example.core.ResponseResult

interface CategoryRepository {

    suspend fun getCategories() : ResponseResult<List<Category>>

}