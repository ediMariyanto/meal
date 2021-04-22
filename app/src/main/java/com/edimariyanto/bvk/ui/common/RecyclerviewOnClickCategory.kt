package com.edimariyanto.bvk.ui.common

import android.view.View
import com.edimariyanto.bvk.data.models.category.Category

interface RecyclerviewOnClickCategory {
    fun onItemClick(view : View, category: Category)
}