package com.edimariyanto.bvk.ui.meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edimariyanto.bvk.R
import com.edimariyanto.bvk.ui.category.CategoryActivity
import com.edimariyanto.bvk.ui.detail.DetailActivity
import com.edimariyanto.bvk.ui.startNewActivity

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