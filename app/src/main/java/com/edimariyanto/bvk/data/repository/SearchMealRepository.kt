package com.edimariyanto.bvk.data.repository

import com.edimariyanto.bvk.data.UserPreferences
import com.edimariyanto.bvk.data.network.CategoryApi
import com.edimariyanto.bvk.data.network.MealApi

class SearchMealRepository (
    private val api: MealApi,
    private val userPreferences: UserPreferences) : BaseRepository() {

    suspend fun searchMeal(mealName: String) = safeApiCall {
        api.searchMeal(mealName)
    }

    suspend fun saveMealId(mealId: String) {
        userPreferences.saveMeal(mealId)
    }

}