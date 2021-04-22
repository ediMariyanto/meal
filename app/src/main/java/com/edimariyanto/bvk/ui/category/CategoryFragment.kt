package com.edimariyanto.bvk.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.edimariyanto.bvk.data.models.category.Category
import com.edimariyanto.bvk.data.network.CategoryApi
import com.edimariyanto.bvk.data.network.Resources
import com.edimariyanto.bvk.data.repository.CategoryRepository
import com.edimariyanto.bvk.databinding.FragmentCategoryBinding
import com.edimariyanto.bvk.ui.base.BaseFragment
import com.edimariyanto.bvk.ui.common.RecyclerviewOnClickCategory
import com.edimariyanto.bvk.ui.handleApiError
import com.edimariyanto.bvk.ui.meal.MealActivity
import com.edimariyanto.bvk.ui.startNewActivity
import com.edimariyanto.bvk.ui.visible
import com.google.gson.JsonObject
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.launch
import okhttp3.*
import okio.ByteString
import okio.ByteString.Companion.decodeHex
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit


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