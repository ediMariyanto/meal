package com.edimariyanto.meal.ui.search


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.edimariyanto.meal.R
import com.edimariyanto.meal.ui.common.RecyclerviewOnClickSearchMeal
import kotlinx.android.synthetic.main.layout_search_meal.view.*

class SearchMealAdapter(private val data: List<com.edimariyanto.meal.data.models.meal.search.Meal>) : RecyclerView.Adapter<MealHolder>() {

    var listener: RecyclerviewOnClickSearchMeal? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder {
        return MealHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_search_meal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MealHolder, position: Int) {
        holder.bindCategory(data[position])

        holder.itemView.setOnClickListener {
                listener?.onItemClick(it, data[position])
        }
    }

    override fun getItemCount(): Int = data.size


}

class MealHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvNameMeal = view.tv_name_layout_seaarch_meal
    private val tvCategoryMeal = view.tv_category_layout_seaarch_meal
    private val tvAreaMeal = view.tv_area_layout_seaarch_meal
    private val imgMeal = view.img_thumb_layout_search_meal

    fun bindCategory(mealItem: com.edimariyanto.meal.data.models.meal.search.Meal) {
        tvNameMeal.text = mealItem.strMeal
        tvAreaMeal.text = mealItem.strArea
        tvCategoryMeal.text = mealItem.strCategory

        val circularProgressDrawable = CircularProgressDrawable(itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(itemView.context)
            .load(mealItem.strMealThumb)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_blank_image)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imgMeal)
    }
}