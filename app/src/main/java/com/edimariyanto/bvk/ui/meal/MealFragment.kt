package com.edimariyanto.bvk.ui.meal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.edimariyanto.bvk.data.UserPreferences
import com.edimariyanto.bvk.data.models.category.Category
import com.edimariyanto.bvk.data.models.meal.Meal
import com.edimariyanto.bvk.data.network.Resources
import com.edimariyanto.bvk.data.network.MealApi
import com.edimariyanto.bvk.data.repository.MealRepository
import com.edimariyanto.bvk.databinding.FragmentMealBinding
import com.edimariyanto.bvk.ui.base.BaseFragment
import com.edimariyanto.bvk.ui.category.CategoryActivity
import com.edimariyanto.bvk.ui.common.RecyclerviewOnClickMeal
import com.edimariyanto.bvk.ui.detail.DetailActivity
import com.edimariyanto.bvk.ui.startNewActivity
import com.edimariyanto.bvk.ui.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MealFragment : BaseFragment<MealViewModel, FragmentMealBinding, MealRepository>(), RecyclerviewOnClickMeal {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressMeal.visible(false)

        val userPreferences = UserPreferences(requireContext())

        userPreferences.category.asLiveData().observe(viewLifecycleOwner, Observer {
            viewModel.getMealByCategory(it.toString())
        })

        viewModel.meal.observe(viewLifecycleOwner, Observer {
            when (it){
                is Resources.Success -> {
                    binding.progressMeal.visible(false)
                    initMealAdapter(it.value.meals)
                }

                is Resources.Loading ->{
                    binding.progressMeal.visible(true)
                }
            }
        })
    }

    private fun initMealAdapter(listData: List<Meal>) {
        binding.rcvMenuMeal.layoutManager = GridLayoutManager(requireContext(), 2)

        val mealAdapter = MealAdapter(listData)
        mealAdapter.listener = this
        binding.rcvMenuMeal.adapter = mealAdapter

        binding.srvMeal.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener  {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mealAdapter.filter.filter(newText)
                return false
            }
        })

        binding.imgSortMeal.setOnClickListener {
            mealAdapter.sortList()
        }

    }

    override fun getViewModel() = MealViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) = FragmentMealBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): MealRepository {
        val api = remoteDataSource.buildApi(MealApi::class.java, "")
        return MealRepository(api, userPreferences,)
    }

    override fun onItemClick(view: View, meal: Meal) {
        lifecycleScope.launch {
            viewModel.saveMeal(meal.idMeal)
            requireActivity().startNewActivity(DetailActivity::class.java)
        }
    }
}