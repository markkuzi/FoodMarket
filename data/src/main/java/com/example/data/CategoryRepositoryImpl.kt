package com.example.data

import com.example.data.network.CategoryService
import com.example.home.domain.CategoryRepository
import com.example.home.domain.entity.Category
import com.example.home.domain.entity.ResponseResult

class CategoryRepositoryImpl(
    private val service: CategoryService
): CategoryRepository {

    override suspend fun getCategories(): ResponseResult<List<Category>> {
        try {
            val response = service.getCategories()
            val list = response.сategories.map {
                Category(
                    id = it.id.toString(),
                    name = it.name,
                    imageUrl = it.imageUrl
                )
            }
            return ResponseResult.Success(list)
        }
        catch (e: Exception) {
            return ResponseResult.Error(e.message)
        }
    }
}