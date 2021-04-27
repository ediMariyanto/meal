package com.edimariyanto.meal.ui.search

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.edimariyanto.meal.data.network.MealApi
import com.edimariyanto.meal.data.network.Resources
import com.edimariyanto.meal.data.repository.SearchMealRepository
import com.edimariyanto.meal.databinding.FragmentSearchMealBinding
import com.edimariyanto.meal.ui.base.BaseFragment
import com.edimariyanto.meal.ui.common.RecyclerviewOnClickSearchMeal
import com.edimariyanto.meal.ui.detail.DetailActivity
import com.edimariyanto.meal.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class SearchMealFragment : BaseFragment<SearchMealViewModel, FragmentSearchMealBinding, SearchMealRepository>(), RecyclerviewOnClickSearchMeal {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressMeal.visible(false)

        viewModel.searchMeal("Beef")

        binding.srvSearchMeal.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val mHandler = Handler()

                mHandler.removeCallbacksAndMessages(null)
                if (newText!!.length >= 3) {
                    mHandler.postDelayed(Runnable {  viewModel.searchMeal(newText)}, 500)
                }
                return false
            }
        })

        viewModel.meal.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resources.Success -> {
                    binding.progressMeal.visible(false)
                    binding.srvSearchMeal.enable(true)
                    initMealAdapter(it.value.meals)
                }

                is Resources.Loading -> {
                    binding.progressMeal.visible(true)
                    binding.srvSearchMeal.enable(false)
                }
            }
        })
    }


    private fun initMealAdapter(listData: List<com.edimariyanto.meal.data.models.meal.search.Meal>) {
        binding.rcvMenuSearchMeal.layoutManager = LinearLayoutManager(requireContext())
        val mealAdapter = SearchMealAdapter(listData)
        mealAdapter.listener = this
        binding.rcvMenuSearchMeal.adapter = mealAdapter


    }

    override fun getViewModel() = SearchMealViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchMealBinding  = FragmentSearchMealBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): SearchMealRepository {
        val token = runBlocking { userPreferences.category.first() }
        val api = remoteDataSource.buildApi(MealApi::class.java, token)
        return SearchMealRepository(api, userPreferences)
    }

    override fun onItemClick(view: View, meal: com.edimariyanto.meal.data.models.meal.search.Meal) {
        lifecycleScope.launch {
            viewModel.saveMealId(meal.idMeal)
            requireActivity().startNewActivity(DetailActivity::class.java)
        }

    }

}