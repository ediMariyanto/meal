package com.edimariyanto.bvk.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.edimariyanto.bvk.data.UserPreferences
import com.edimariyanto.bvk.data.network.RemoteDataSources
import com.edimariyanto.bvk.data.network.MealApi
import com.edimariyanto.bvk.data.repository.BaseRepository
import com.edimariyanto.bvk.ui.category.CategoryActivity
import com.edimariyanto.bvk.ui.startNewActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM: BaseViewModel, B: ViewBinding, R: BaseRepository> : Fragment() {


    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected val remoteDataSource =  RemoteDataSources()
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        return binding.root
    }


    abstract fun getViewModel() :  Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) : B

    abstract fun getFragmentRepository() : R

}