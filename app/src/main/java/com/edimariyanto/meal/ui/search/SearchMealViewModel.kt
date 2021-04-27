package com.edimariyanto.meal.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edimariyanto.meal.data.models.meal.search.SearchMealResponse
import com.edimariyanto.meal.data.network.Resources
import com.edimariyanto.meal.data.repository.SearchMealRepository
import com.edimariyanto.meal.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchMealViewModel(private val searchMealRepository: SearchMealRepository): BaseViewModel(searchMealRepository) {

    private val _meal: MutableLiveData<Resources<SearchMealResponse>> = MutableLiveData()

    val meal: LiveData<Resources<SearchMealResponse>>
    get() = _meal


    fun searchMeal(mealName: String) = viewModelScope.launch{
        _meal.value = Resources.Loading
        _meal.value =  searchMealRepository.searchMeal(mealName)

    }

    suspend fun saveMealId(mealId: String){
        searchMealRepository.saveMealId(mealId)
    }

}