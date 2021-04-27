package com.edimariyanto.meal.ui.detail


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edimariyanto.meal.R
import kotlinx.android.synthetic.main.layout_ingredients.view.*

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