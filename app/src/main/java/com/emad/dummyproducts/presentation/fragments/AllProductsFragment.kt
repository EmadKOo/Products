package com.emad.dummyproducts.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.emad.dummyproducts.databinding.FragmentAllProductsBinding
import com.emad.dummyproducts.presentation.viewmodel.MainViewModel
import com.emad.dummyproducts.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllProductsFragment : Fragment() {
    lateinit var mBinding: FragmentAllProductsBinding
    val mainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentAllProductsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAllProducts(1)
    }
    
    private fun loadAllProducts(page: Int) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.getAllProducts(page)
            mainViewModel.allProductsStateFlow.collectLatest {
                when(it){
                    is Resource.Error -> {
                        Log.d(TAG, "loadAllProducts: ERROR " + it.data)
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "loadAllProducts: Loading")
                    }
                    is Resource.Success -> {
                        Log.d(TAG, "loadAllProducts: SUCCESS " + it.data)
                    }
                }
            }
        }
    }
}

private const val TAG = "AllProductsFragment"