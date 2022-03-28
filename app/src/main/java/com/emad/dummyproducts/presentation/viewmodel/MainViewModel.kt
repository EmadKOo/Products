package com.emad.dummyproducts.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.emad.dummyproducts.data.model.AllProductsResponse
import com.emad.dummyproducts.domain.repository.MainRepository
import com.emad.dummyproducts.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel constructor(private val repository: MainRepository): ViewModel() {
    val allProductsStateFlow= repository.getAllProducts()
}