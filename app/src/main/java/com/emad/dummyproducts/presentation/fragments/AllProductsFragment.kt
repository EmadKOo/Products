package com.emad.dummyproducts.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.emad.dummyproducts.databinding.FragmentAllProductsBinding
import com.emad.dummyproducts.domain.listeners.ProductSelected
import com.emad.dummyproducts.presentation.adapters.ProductsAdapter
import com.emad.dummyproducts.presentation.base.BaseFragment
import com.emad.dummyproducts.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllProductsFragment : BaseFragment(), ProductSelected {
    lateinit var productsAdapter: ProductsAdapter
    lateinit var mBinding: FragmentAllProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsAdapter = ProductsAdapter()
        productsAdapter.submitProductSelectedListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentAllProductsBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()
        loadAllProducts()
    }

    private fun initRecyclerview(){
        mBinding.productsRecyclerView.adapter= productsAdapter
    }

    private fun loadAllProducts() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mainViewModel.allProductsStateFlow.collectLatest {
                productsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }
    }

    override fun OnProductSelected(productID: Int) {
        findNavController().navigate(AllProductsFragmentDirections.actionAllProductsFragmentToProductDetailsFragment(productID))
    }
}