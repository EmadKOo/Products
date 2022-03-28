package com.emad.dummyproducts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.emad.dummyproducts.data.model.AllProductsResponse
import com.emad.dummyproducts.data.model.ProductDetails
import com.emad.dummyproducts.domain.repository.MainRepository
import com.emad.dummyproducts.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel constructor(private val repository: MainRepository): ViewModel() {
    private val _productDetailsStateFlow = MutableStateFlow<Resource<ProductDetails>>(Resource.Init())
    val productDetailsStateFlow: StateFlow<Resource<ProductDetails>> = _productDetailsStateFlow

    val allProductsStateFlow= repository.getAllProducts()

    fun getProductDetails(productID: Int)= viewModelScope.launch {
        try {
            _productDetailsStateFlow.emit(Resource.Loading())
            _productDetailsStateFlow.emit(Resource.Success(repository.getProductDetails(productID)))
        }catch (ex: Exception){
            _productDetailsStateFlow.emit(Resource.Error(ex.localizedMessage))
        }
    }
}