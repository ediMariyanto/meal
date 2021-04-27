package com.edimariyanto.meal.ui.common

import android.view.View

interface RecyclerviewOnClickSearchMeal {
    fun onItemClick(view : View, meal: com.edimariyanto.meal.data.models.meal.search.Meal)
}