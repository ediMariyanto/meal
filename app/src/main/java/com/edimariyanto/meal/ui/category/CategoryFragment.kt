package com.edimariyanto.meal.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.edimariyanto.meal.data.models.category.Category
import com.edimariyanto.meal.data.network.CategoryApi
import com.edimariyanto.meal.data.network.Resources
import com.edimariyanto.meal.data.repository.CategoryRepository
import com.edimariyanto.meal.databinding.FragmentCategoryBinding
import com.edimariyanto.meal.ui.base.BaseFragment
import com.edimariyanto.meal.ui.common.RecyclerviewOnClickCategory
import com.edimariyanto.meal.ui.handleApiError
import com.edimariyanto.meal.ui.meal.MealActivity
import com.edimariyanto.meal.ui.startNewActivity
import kotlinx.coroutines.launch
import okhttp3.*


class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding, CategoryRepository>(), RecyclerviewOnClickCategory {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressCategory.visible(false)

        getCategory()

        viewModel.categoryResponse.observe(viewLifecycleOwner, {
            binding.progressCategory.visible(it is Resources.Loading)
            when (it) {
                is Resources.Success -> {
                    lifecycleScope.launch {
                        Log.d("cetak response category", it.toString())
                        initCategoryAdapter(it.value.categories)
                    }
                }
                is Resources.Failure -> handleApiError(it) { getCategory() }
            }
        })

    }

    private fun initCategoryAdapter(listData: List<Category>) {
        binding.rcvMenuCategory.layoutManager = GridLayoutManager(requireContext(), 2)
//        binding.rcvMenuCategory.setHasFixedSize(true)

        val categoryAdapter = CategoryAdapter(listData)
        categoryAdapter.listener = this
        binding.rcvMenuCategory.adapter = categoryAdapter

        binding.srvCategory.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                categoryAdapter.filter.filter(newText)
                return false
            }
        })

        binding.imgSortCategory.setOnClickListener {
            categoryAdapter.sortList()
        }

    }

    private fun getCategory() {
        binding.progressCategory.visible(true)
        viewModel.getCategory()
    }

    override fun getViewModel() = CategoryViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCategoryBinding.inflate(inflater, container, false)


    override fun getFragmentRepository() = CategoryRepository(
        remoteDataSource.buildApi(CategoryApi::class.java),
        userPreferences
    )

    override fun onItemClick(view: View, category: Category) {
        lifecycleScope.launch {
            viewModel.saveCategory(category.strCategory)
            requireActivity().startNewActivity(MealActivity::class.java)
        }
    }

}