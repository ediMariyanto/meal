package com.edimariyanto.bvk.data.network

import okhttp3.ResponseBody

sealed class Resources<out T> {
    data class Success<out T>(val value: T) : Resources<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode : Int?,
        val errorBody : ResponseBody?
    ) : Resources<Nothing>()

    object Loading : Resources<Nothing>()
}