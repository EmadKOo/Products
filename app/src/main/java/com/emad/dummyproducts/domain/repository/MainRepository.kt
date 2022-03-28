package com.emad.dummyproducts.domain.repository

import com.emad.dummyproducts.data.model.AllProductsResponse

interface MainRepository {
    suspend fun getAllProducts(page: Int): AllProductsResponse
}