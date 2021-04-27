package com.edimariyanto.meal.data.repository

import com.edimariyanto.meal.data.UserPreferences
import com.edimariyanto.meal.data.network.MealApi

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