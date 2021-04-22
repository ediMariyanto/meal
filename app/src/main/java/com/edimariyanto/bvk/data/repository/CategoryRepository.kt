package com.edimariyanto.bvk.data.repository

import com.edimariyanto.bvk.data.UserPreferences
import com.edimariyanto.bvk.data.network.CategoryApi

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