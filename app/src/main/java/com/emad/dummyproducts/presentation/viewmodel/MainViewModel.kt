package com.emad.dummyproducts.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emad.dummyproducts.data.model.AllProductsResponse
import com.emad.dummyproducts.domain.repository.MainRepository
import com.emad.dummyproducts.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel constructor(private val repository: MainRepository): ViewModel() {
    private val _allProductsStateFlow = MutableStateFlow<Resource<AllProductsResponse>>(Resource.Init())
    val allProductsStateFlow: StateFlow<Resource<AllProductsResponse>> = _allProductsStateFlow

    suspend fun getAllProducts(page: Int)= viewModelScope.launch {
        try {
            _allProductsStateFlow.emit(Resource.Loading())
            _allProductsStateFlow.emit(Resource.Success(repository.getAllProducts(page)))
        }catch (ex: Exception){
            _allProductsStateFlow.emit(Resource.Error(ex.localizedMessage))
        }
    }
}