package com.edimariyanto.bvk.ui.common

import android.view.View
import com.edimariyanto.bvk.data.models.category.Category
import com.edimariyanto.bvk.data.models.meal.Meal

interface RecyclerviewOnClickMeal {
    fun onItemClick(view : View, meal: Meal)
}