package com.edimariyanto.meal.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edimariyanto.meal.R
import com.edimariyanto.meal.ui.meal.MealActivity
import com.edimariyanto.meal.ui.startNewActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        this.startNewActivity(MealActivity::class.java)
    }
}