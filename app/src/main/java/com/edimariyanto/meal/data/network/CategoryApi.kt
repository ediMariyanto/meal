package com.edimariyanto.meal.data.network

import com.edimariyanto.meal.data.models.category.CategoryResponse
import retrofit2.http.GET

interface CategoryApi {

    @GET("categories.php")
    suspend fun getCategory(): CategoryResponse

}