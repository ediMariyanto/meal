package com.edimariyanto.meal.ui.common

import android.view.View
import com.edimariyanto.meal.data.models.category.Category

interface RecyclerviewOnClickCategory {
    fun onItemClick(view : View, category: Category)
}