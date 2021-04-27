package com.edimariyanto.meal.ui.meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edimariyanto.meal.R
import com.edimariyanto.meal.ui.category.CategoryActivity
import com.edimariyanto.meal.ui.startNewActivity

class MealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        this.startNewActivity(CategoryActivity::class.java)
    }

}