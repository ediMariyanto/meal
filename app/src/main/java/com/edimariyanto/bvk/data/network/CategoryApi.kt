package com.edimariyanto.bvk.data.network

import com.edimariyanto.bvk.data.models.category.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryApi {

    @GET("categories.php")
    suspend fun getCategory(): CategoryResponse

}