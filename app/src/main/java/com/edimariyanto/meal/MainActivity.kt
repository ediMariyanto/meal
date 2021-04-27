package com.edimariyanto.meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edimariyanto.meal.ui.category.CategoryActivity
import com.edimariyanto.meal.ui.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startNewActivity(CategoryActivity::class.java)

    }
}
