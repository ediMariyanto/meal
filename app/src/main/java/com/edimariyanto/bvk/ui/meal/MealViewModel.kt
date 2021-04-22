package com.edimariyanto.bvk.ui.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edimariyanto.bvk.data.models.meal.MealREsponse
import com.edimariyanto.bvk.data.network.Resources
import com.edimariyanto.bvk.data.repository.MealRepository
import com.edimariyanto.bvk.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MealViewModel(private val mealRepository: MealRepository): BaseViewModel(mealRepository) {

    private val _meal: MutableLiveData<Resources<MealREsponse>> = MutableLiveData()
    val meal: LiveData<Resources<MealREsponse>>
    get() = _meal


    fun getMealByCategory(categoryName: String) = viewModelScope.launch{
        _meal.value = Resources.Loading
        _meal.value =  mealRepository.getMealByCategory(categoryName)

    }

    suspend fun saveMeal(mealId: String){
        mealRepository.saveMeal(mealId)
    }

}