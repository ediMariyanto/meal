package com.edimariyanto.meal.data.repository

import com.edimariyanto.meal.data.UserPreferences
import com.edimariyanto.meal.data.network.CategoryApi

class CategoryRepository(
    private val api: CategoryApi,
    private val userPreferences: UserPreferences

) : BaseRepository() {

    suspend fun getCategory() = safeApiCall {
        api.getCategory()
    }

    suspend fun saveCategory(categoryName: String) {
        userPreferences.saveCategory(categoryName)
    }

}