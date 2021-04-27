package com.edimariyanto.meal.ui.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.edimariyanto.meal.R
import com.edimariyanto.meal.data.UserPreferences
import com.edimariyanto.meal.data.models.detail.Meal
import com.edimariyanto.meal.data.network.MealApi
import com.edimariyanto.meal.data.network.Resources
import com.edimariyanto.meal.data.repository.DetailRepository
import com.edimariyanto.meal.databinding.FragmentDetailBinding
import com.edimariyanto.meal.ui.base.BaseFragment
import com.edimariyanto.meal.ui.search.SearchMealActivity
import com.edimariyanto.meal.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class DetailFragment :
    BaseFragment<MealDetailViewModel, FragmentDetailBinding, DetailRepository>() {

    var urlYoutube: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTagsDetailMeal.visible(false)
        binding.tvCategoryDetailMeal.visible(false)
        binding.tvDrinkDetailMeal.visible(false)

        val userPreferences = UserPreferences(requireContext())

        userPreferences.mealId.asLiveData().observe(viewLifecycleOwner, Observer {
            viewModel.getDetailMealById(it.toString())
        })

        viewModel.mealDetail.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resources.Success -> {
                    binding.progressbarDetail.visible(false)
                    updateUI(it.value.meals.get(0))
                }

                is Resources.Loading -> {
                    binding.progressbarDetail.visible(true)
                }
            }
        })

        binding.btnSearchVideoDetail.setOnClickListener {
            requireActivity().startNewActivity(SearchMealActivity::class.java)
        }

        binding.btnWatchVideoDetail.setOnClickListener {
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=BiQUYTBb6eQ"))

            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=BiQUYTBb6eQ")
            )
            try {
                startActivity(appIntent)
            } catch (ex: ActivityNotFoundException) {
                startActivity(webIntent)
            }
        }
    }

    fun updateUI(detailMeal: Meal) {
        binding.tvMealNameDetailMeal.text = detailMeal.strMeal
        binding.tvDescDetailMeal.text = detailMeal.strInstructions

        urlYoutube = detailMeal.strYoutube

        if (detailMeal.strTags != ""){
            detailMeal.strTags?.let {
                binding.tvTagsDetailMeal.visible(true)
                binding.tvTagsDetailMeal.text = detailMeal.strTags
            }
        }

        if (detailMeal.strCategory != ""){
            detailMeal.strCategory?.let {
                binding.tvCategoryDetailMeal.visible(true)
                binding.tvCategoryDetailMeal.text = detailMeal.strCategory
            }
        }

        if (detailMeal.strDrinkAlternate != ""){
            detailMeal.strDrinkAlternate?.let {
                binding.tvDrinkDetailMeal.visible(true)
                binding.tvDrinkDetailMeal.text = detailMeal.strDrinkAlternate
            }
        }

        val listIngradients: MutableList<String> = mutableListOf()

        if (detailMeal.strIngredient1 != ""){
            detailMeal.strIngredient1?.let {
                listIngradients.add(detailMeal.strIngredient1 + ": " + detailMeal.strMeasure1)
            }
        }
        if (detailMeal.strIngredient2 != "" ){
            detailMeal.strIngredient2?.let { listIngradients.add(detailMeal.strIngredient2 + ": " + detailMeal.strMeasure2) }
        }
        if (detailMeal.strIngredient3 != ""){
            detailMeal.strIngredient3?.let {
                listIngradients.add(detailMeal.strIngredient3 + ": " + detailMeal.strMeasure3)
            }
        }
        if (detailMeal.strIngredient4 != ""){
            detailMeal.strIngredient4?.let {
                listIngradients.add(detailMeal.strIngredient4 + ": " + detailMeal.strMeasure4)
            }
        }
        if (detailMeal.strIngredient5 != ""){
            detailMeal.strIngredient5?.let {
                listIngradients.add(detailMeal.strIngredient5 + ": " + detailMeal.strMeasure5)
            }
        }
        if (detailMeal.strIngredient6 != ""){
            detailMeal.strIngredient6?.let {
                listIngradients.add(detailMeal.strIngredient6 + ": " + detailMeal.strMeasure6)
            }
        }
        if (detailMeal.strIngredient7 != ""){
            detailMeal.strIngredient7?.let {
                listIngradients.add(detailMeal.strIngredient7 + ": " + detailMeal.strMeasure7)
            }
        }
        if (detailMeal.strIngredient8 != ""){
            detailMeal.strIngredient8?.let {
                listIngradients.add(detailMeal.strIngredient8 + ": " + detailMeal.strMeasure8)
            }

        }
        if (detailMeal.strIngredient9 != ""){
            detailMeal.strIngredient9?.let {
                listIngradients.add(detailMeal.strIngredient9 + ": " + detailMeal.strMeasure9)
            }
        }
        if (detailMeal.strIngredient10 != ""){
            detailMeal.strIngredient10?.let {
                listIngradients.add(detailMeal.strIngredient10 + ": " + detailMeal.strMeasure10)
            }
        }
        if (detailMeal.strIngredient11 != ""){
            detailMeal.strIngredient11?.let {
                listIngradients.add(detailMeal.strIngredient11 + ": " + detailMeal.strMeasure11)
            }
        }
        if (detailMeal.strIngredient12 != "" ){
            detailMeal.strIngredient12?.let {
                listIngradients.add(detailMeal.strIngredient12 + ": " + detailMeal.strMeasure12)
            }
        }
        if (detailMeal.strIngredient13 != "" ){
            detailMeal.strIngredient13?.let {
                listIngradients.add(detailMeal.strIngredient13 + ": " + detailMeal.strMeasure13)
            }

        }
        if (detailMeal.strIngredient14 != ""){
            detailMeal.strIngredient14?.let {
                listIngradients.add(detailMeal.strIngredient14 + ": " + detailMeal.strMeasure14)
            }

        }
        if (detailMeal.strIngredient15 != ""){
            detailMeal.strIngredient15?.let {
                listIngradients.add(detailMeal.strIngredient15 + ": " + detailMeal.strMeasure15)
            }

        }
        if (detailMeal.strIngredient16 != ""){
            detailMeal.strIngredient16?.let {
                listIngradients.add(detailMeal.strIngredient16 + ": " + detailMeal.strMeasure16)
            }

        }
        if (detailMeal.strIngredient17 != ""){
            detailMeal.strIngredient17?.let {
                listIngradients.add(detailMeal.strIngredient17 + ": " + detailMeal.strMeasure17)
            }

        }
        if (detailMeal.strIngredient18 != ""){
            detailMeal.strIngredient18?.let {
                listIngradients.add(detailMeal.strIngredient18 + ": " + detailMeal.strMeasure18)
            }

        }
        if (detailMeal.strIngredient19 != "" ){
            detailMeal.strIngredient19?.let {
                listIngradients.add(detailMeal.strIngredient19 + "19: " + detailMeal.strMeasure19)
            }

        }
        if (detailMeal.strIngredient20 != ""){
            detailMeal.strIngredient20?.let {
                listIngradients.add(detailMeal.strIngredient20 + ": " + detailMeal.strMeasure20)
            }
        }

        binding.rcvIngradientDetail.layoutManager = GridLayoutManager(requireContext(), 2)


        val detailAdapter = DetailAdapter(listIngradients)
        binding.rcvIngradientDetail.adapter = detailAdapter


        val circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(requireView())
            .load(detailMeal.strMealThumb)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_blank_image)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.imgDetailMeal)

    }

    override fun getViewModel() = MealDetailViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentDetailBinding.inflate(
            inflater,
            container,
            false
        )

    override fun getFragmentRepository(): DetailRepository {
        val token = runBlocking { userPreferences.category.first() }
        val api = remoteDataSource.buildApi(MealApi::class.java, token)
        return DetailRepository(api)
    }

}