package com.edimariyanto.bvk.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edimariyanto.bvk.R
import com.edimariyanto.bvk.ui.detail.DetailActivity
import com.edimariyanto.bvk.ui.meal.MealActivity
import com.edimariyanto.bvk.ui.startNewActivity

class SearchMealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meal)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        this.startNewActivity(DetailActivity::class.java)
    }
}