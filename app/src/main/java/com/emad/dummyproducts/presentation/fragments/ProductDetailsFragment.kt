package com.emad.dummyproducts.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.emad.dummyproducts.R
import com.emad.dummyproducts.databinding.FragmentProductDetailsBinding
import com.emad.dummyproducts.presentation.viewmodel.MainViewModel
import com.emad.dummyproducts.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsFragment : Fragment() {

    lateinit var mBinding: FragmentProductDetailsBinding
    val mainViewModel by viewModel<MainViewModel>()
    val args by navArgs<ProductDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProductDetails(args.productID)
    }

    private fun getProductDetails(productID: Int) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.getProductDetails(productID)
            mainViewModel.productDetailsStateFlow.collectLatest {
                when (it) {
                    is Resource.Error -> {
                        Log.d(TAG, "getProductDetails: ERROR " + it.data)
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "getProductDetails: Loading")
                    }
                    is Resource.Success -> {
                        Log.d(TAG, "getProductDetails: SUCCESS " + it.data)
                    }
                }
            }
        }
    }
}

private const val TAG = "ProductDetailsFragment"