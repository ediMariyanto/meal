package com.edimariyanto.bvk.ui.category

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edimariyanto.bvk.R

class CategoryActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
    }

    override fun onBackPressed() {
        if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true
            Toast.makeText(this, "Please click BACK again to exit.", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        } else {
            finishAffinity()
        }
    }
}