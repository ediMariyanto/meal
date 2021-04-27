package com.edimariyanto.meal.ui.base

import androidx.lifecycle.ViewModel
import com.edimariyanto.meal.data.repository.BaseRepository

abstract class BaseViewModel(private val baseRepository: BaseRepository) : ViewModel() {

}