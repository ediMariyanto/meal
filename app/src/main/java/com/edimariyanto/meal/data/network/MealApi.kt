package com.edimariyanto.meal.data.network

import com.edimariyanto.meal.data.models.detail.DetailMealResponse
import com.edimariyanto.meal.data.models.meal.MealREsponse
import com.edimariyanto.meal.data.models.meal.search.SearchMealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {


    @GET("filter.php")
    suspend fun getMealByCategory(@Query("c") categoryName: String): MealREsponse

    @GET("lookup.php")
    suspend fun getDetailMealById(@Query("i") mealId: String): DetailMealResponse

    @GET("search.php")
    suspend fun searchMeal(@Query("s") mealName: String): SearchMealResponse
}