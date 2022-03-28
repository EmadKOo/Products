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
import com.emad.dummyproducts.presentation.base.BaseFragment
import com.emad.dummyproducts.presentation.viewmodel.MainViewModel
import com.emad.dummyproducts.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsFragment : BaseFragment() {

    lateinit var mBinding: FragmentProductDetailsBinding
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
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        mBinding.product = it.data?.data
                    }
                }
            }
        }
    }
}

private const val TAG = "ProductDetailsFragment"