package com.edimariyanto.meal.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edimariyanto.meal.data.models.category.CategoryResponse
import com.edimariyanto.meal.data.network.Resources
import com.edimariyanto.meal.data.repository.CategoryRepository
import com.edimariyanto.meal.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryRepository: CategoryRepository) : BaseViewModel(categoryRepository) {

    private val _categoryResponse: MutableLiveData<Resources<CategoryResponse>> = MutableLiveData()
    val categoryResponse: LiveData<Resources<CategoryResponse>>
        get() = _categoryResponse


    fun getCategory() = viewModelScope.launch{
        _categoryResponse.value = Resources.Loading
        _categoryResponse.value = categoryRepository.getCategory()
    }


    suspend fun saveCategory(categoryName: String){
        categoryRepository.saveCategory(categoryName)
    }
}