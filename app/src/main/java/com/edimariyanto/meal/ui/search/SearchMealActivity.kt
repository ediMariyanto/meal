package com.edimariyanto.meal.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edimariyanto.meal.R
import com.edimariyanto.meal.ui.detail.DetailActivity
import com.edimariyanto.meal.ui.startNewActivity

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