package com.edimariyanto.meal.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.clear
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(
            name = "settings"
    )

    val category: Flow<String?>
    get() = dataStore.data.map {
        it[KEY_CATEGORY]
    }

    val mealId: Flow<String?>
        get() = dataStore.data.map {
            it[KEY_MEAL]
        }

    suspend fun saveCategory(categoryName: String){
        dataStore.edit {
            it[KEY_CATEGORY] = categoryName
        }
    }

    suspend fun saveMeal(mealId: String){
        dataStore.edit {
            it[KEY_MEAL] = mealId
        }
    }


    suspend fun clear(){
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object{
        private val KEY_CATEGORY = preferencesKey<String>("key_category")
        private val KEY_MEAL = preferencesKey<String>("key_meal")
    }

}