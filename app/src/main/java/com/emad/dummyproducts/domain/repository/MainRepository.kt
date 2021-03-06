package com.emad.dummyproducts.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.emad.dummyproducts.data.model.AllProductsResponse
import com.emad.dummyproducts.data.model.ProductDetails
import com.emad.dummyproducts.data.paging.ProductsPagingSource
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getAllProducts(): Flow<PagingData<AllProductsResponse.Product>>

    suspend fun getProductDetails(productID: Int): ProductDetails
}