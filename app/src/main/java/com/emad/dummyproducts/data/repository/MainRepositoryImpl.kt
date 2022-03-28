package com.emad.dummyproducts.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.emad.dummyproducts.data.datasources.remote.ApiService
import com.emad.dummyproducts.data.model.AllProductsResponse
import com.emad.dummyproducts.data.paging.ProductsPagingSource
import com.emad.dummyproducts.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(private val apiService: ApiService): MainRepository {

    override fun getAllProducts(): Flow<PagingData<AllProductsResponse.Product>> {
       return Pager(
           config = PagingConfig(
               pageSize = 5,
               maxSize = 20,
               enablePlaceholders = false
           ),
           pagingSourceFactory = {ProductsPagingSource(apiService)}
       ).flow
    }


}