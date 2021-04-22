package com.edimariyanto.bvk.ui.category


import android.util.Log
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
import com.edimariyanto.bvk.ui.common.RecyclerviewOnClickCategory
import kotlinx.android.synthetic.main.layout_category_meal.view.*
import java.util.*
import kotlin.collections.ArrayList


class CategoryAdapter(private val data: List<Category>) : RecyclerView.Adapter<CategoryHolder>(), Filterable {

    var listener: RecyclerviewOnClickCategory? = null
    var categoryFilterList = ArrayList<Category>()

    var isSort: Boolean = false

    init {
        categoryFilterList = data as ArrayList<Category>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_category_meal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bindCategory(categoryFilterList[position])

        holder.itemView.setOnClickListener {
                listener?.onItemClick(it, categoryFilterList[position])
        }

        holder.itemView.btn_get_meal_category.setOnClickListener{
                listener?.onItemClick(it, categoryFilterList[position])
        }

        holder.itemView.tv_desc_layout_catogory.setOnClickListener{}
    }

    override fun getItemCount(): Int = categoryFilterList.size


    fun sortList(){
        isSort = if (isSort){
            categoryFilterList.reverse()
            false
        } else{
            categoryFilterList.sortWith { lhs, rhs -> lhs.strCategory.compareTo(rhs.strCategory) }
            true
        }

        notifyDataSetChanged()

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    categoryFilterList = data as ArrayList<Category>
                } else {
                    val resultList = ArrayList<Category>()
                    for (row in data) {
                        if (row.strCategory.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )) {
                            resultList.add(row)
                        }
                    }
                    categoryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = categoryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                categoryFilterList = results?.values as ArrayList<Category>
                notifyDataSetChanged()
            }

        }
    }

}

class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvNameCategory = view.tv_name_layout_category
    private val imgCategory = view.img_thumb_layout_category
    private val tvDescCategory = view.tv_desc_layout_catogory


    fun bindCategory(categoryItem: Category) {
        tvNameCategory.text = categoryItem.strCategory
        tvDescCategory.text = categoryItem.strCategoryDescription

        val circularProgressDrawable = CircularProgressDrawable(itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(itemView.context)
            .load(categoryItem.strCategoryThumb)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_blank_image)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imgCategory)

    }
}