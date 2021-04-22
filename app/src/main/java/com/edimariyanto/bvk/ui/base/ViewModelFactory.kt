package com.edimariyanto.bvk.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edimariyanto.bvk.data.repository.*
import com.edimariyanto.bvk.ui.category.CategoryViewModel
import com.edimariyanto.bvk.ui.detail.MealDetailViewModel
import com.edimariyanto.bvk.ui.meal.MealViewModel
import com.edimariyanto.bvk.ui.search.SearchMealViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository : BaseRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> CategoryViewModel(repository as CategoryRepository) as T
            modelClass.isAssignableFrom(MealViewModel::class.java) -> MealViewModel(repository as MealRepository) as T
            modelClass.isAssignableFrom(MealDetailViewModel::class.java) -> MealDetailViewModel(repository as DetailRepository) as T
            modelClass.isAssignableFrom(SearchMealViewModel::class.java) -> SearchMealViewModel(repository as SearchMealRepository) as T
            // TODO : Add another viewModel here
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}