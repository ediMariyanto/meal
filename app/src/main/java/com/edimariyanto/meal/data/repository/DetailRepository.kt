package com.edimariyanto.meal.data.repository

import com.edimariyanto.meal.data.network.MealApi

class DetailRepository(private val api: MealApi) : BaseRepository() {

    suspend fun getDetailMealById(meailId: String) = safeApiCall {
        api.getDetailMealById(meailId)
    }

}