package com.edimariyanto.bvk.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edimariyanto.bvk.R
import com.edimariyanto.bvk.ui.category.CategoryActivity
import com.edimariyanto.bvk.ui.meal.MealActivity
import com.edimariyanto.bvk.ui.startNewActivity

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