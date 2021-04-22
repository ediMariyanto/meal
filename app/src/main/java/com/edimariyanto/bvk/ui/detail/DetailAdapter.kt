package com.edimariyanto.bvk.ui.detail


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.edimariyanto.bvk.R
import com.edimariyanto.bvk.data.models.category.Category
import com.edimariyanto.bvk.data.models.meal.Meal
import com.edimariyanto.bvk.ui.common.RecyclerviewOnClickMeal
import com.edimariyanto.bvk.ui.common.RecyclerviewOnClickSearchMeal
import kotlinx.android.synthetic.main.layout_ingredients.view.*
import kotlinx.android.synthetic.main.layout_meal.view.*
import kotlinx.android.synthetic.main.layout_search_meal.view.*
import java.util.*
import kotlin.collections.ArrayList

class DetailAdapter(private val data: List<String>) : RecyclerView.Adapter<DetailHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHolder {
        return DetailHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_ingredients, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DetailHolder, position: Int) {
        holder.bindCategory(data[position])
    }

    override fun getItemCount(): Int = data.size

}

class DetailHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvNameMeal = view.tv_ingredients_detail_meal

    fun bindCategory(detailMeal: String) {
        tvNameMeal.text = detailMeal
    }
}