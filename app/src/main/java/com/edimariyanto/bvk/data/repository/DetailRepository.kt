package com.edimariyanto.bvk.data.repository

import com.edimariyanto.bvk.data.network.MealApi

class DetailRepository(private val api: MealApi) : BaseRepository() {

    suspend fun getDetailMealById(meailId: String) = safeApiCall {
        api.getDetailMealById(meailId)
    }

}