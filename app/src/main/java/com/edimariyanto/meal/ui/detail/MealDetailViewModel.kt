package com.edimariyanto.meal.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edimariyanto.meal.data.models.detail.DetailMealResponse
import com.edimariyanto.meal.data.network.Resources
import com.edimariyanto.meal.data.repository.DetailRepository
import com.edimariyanto.meal.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MealDetailViewModel(private val mealRepository: DetailRepository): BaseViewModel(mealRepository) {

    private val _meal: MutableLiveData<Resources<DetailMealResponse>> = MutableLiveData()
    val mealDetail: LiveData<Resources<DetailMealResponse>>
    get() = _meal


    fun getDetailMealById(mealId: String) = viewModelScope.launch{
        _meal.value = Resources.Loading
        _meal.value =  mealRepository.getDetailMealById(mealId)

    }

}