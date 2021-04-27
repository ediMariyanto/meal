package com.edimariyanto.meal.data.repository

import com.edimariyanto.meal.data.UserPreferences
import com.edimariyanto.meal.data.network.MealApi

class MealRepository(
    private val api: MealApi,
    private val userPreferences: UserPreferences
    ) : BaseRepository() {

    suspend fun getMealByCategory(categoryName: String) = safeApiCall {
        api.getMealByCategory(categoryName)
    }

    suspend fun saveMeal(mealId: String) {
        userPreferences.saveMeal(mealId)
    }

}