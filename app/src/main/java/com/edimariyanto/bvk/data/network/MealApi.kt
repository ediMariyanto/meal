package com.edimariyanto.bvk.data.network

import com.edimariyanto.bvk.data.models.detail.DetailMealResponse
import com.edimariyanto.bvk.data.models.meal.MealREsponse
import com.edimariyanto.bvk.data.models.meal.search.SearchMealResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MealApi {


    @GET("filter.php")
    suspend fun getMealByCategory(@Query("c") categoryName: String): MealREsponse

    @GET("lookup.php")
    suspend fun getDetailMealById(@Query("i") mealId: String): DetailMealResponse

    @GET("search.php")
    suspend fun searchMeal(@Query("s") mealName: String): SearchMealResponse
}