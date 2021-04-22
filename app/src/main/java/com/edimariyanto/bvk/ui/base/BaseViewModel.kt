package com.edimariyanto.bvk.ui.base

import androidx.lifecycle.ViewModel
import com.edimariyanto.bvk.data.network.MealApi
import com.edimariyanto.bvk.data.repository.BaseRepository

abstract class BaseViewModel(private val baseRepository: BaseRepository) : ViewModel() {

}