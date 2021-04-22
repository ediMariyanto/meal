package com.edimariyanto.bvk.ui.meal


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
import kotlinx.android.synthetic.main.layout_meal.view.*
import java.util.*
import kotlin.collections.ArrayList

class MealAdapter(private val data: List<Meal>) : RecyclerView.Adapter<MealHolder>(), Filterable {

    var isSort: Boolean = false

    var listener: RecyclerviewOnClickMeal? = null

    var mealFilterList = ArrayList<Meal>()

    init {
        mealFilterList = data as ArrayList<Meal>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder {
        return MealHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_meal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MealHolder, position: Int) {
        holder.bindCategory(mealFilterList[position])

        holder.itemView.setOnClickListener {
                listener?.onItemClick(it, mealFilterList[position])
        }
    }

    override fun getItemCount(): Int = mealFilterList.size

    fun sortList(){
        isSort = if (isSort){
            mealFilterList.reverse()
            false
        } else{
            mealFilterList.sortWith { lhs, rhs -> lhs.strMeal.compareTo(rhs.strMeal) }
            true
        }

        notifyDataSetChanged()

    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    mealFilterList = data as ArrayList<Meal>
                } else {
                    val resultList = ArrayList<Meal>()
                    for (row in data) {
                        if (row.strMeal.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(
                                Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    mealFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = mealFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mealFilterList = results?.values as ArrayList<Meal>
                notifyDataSetChanged()
            }

        }
    }
}

class MealHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvNameMeal = view.tv_name_layout_meal
    private val imgMeal = view.img_thumb_layout_meal


    fun bindCategory(mealItem: Meal) {
        tvNameMeal.text = mealItem.strMeal


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