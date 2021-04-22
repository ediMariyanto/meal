package com.edimariyanto.bvk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.edimariyanto.bvk.data.UserPreferences
import com.edimariyanto.bvk.ui.category.CategoryActivity
import com.edimariyanto.bvk.ui.meal.MealActivity
import com.edimariyanto.bvk.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startNewActivity(CategoryActivity::class.java)

    }
}
