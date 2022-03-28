package com.emad.dummyproducts.data.repository

import com.emad.dummyproducts.data.datasources.remote.ApiService
import com.emad.dummyproducts.data.model.AllProductsResponse
import com.emad.dummyproducts.domain.repository.MainRepository

class MainRepositoryImpl(private val apiService: ApiService): MainRepository {
    override suspend fun getAllProducts(page: Int): AllProductsResponse {
        return apiService.getAllProducts(page= page)
    }
}