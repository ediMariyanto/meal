package com.edimariyanto.meal.ui.common

import android.view.View
import com.edimariyanto.meal.data.models.meal.Meal

interface RecyclerviewOnClickMeal {
    fun onItemClick(view : View, meal: Meal)
}