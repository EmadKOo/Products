package com.emad.dummyproducts.data.datasources.remote

import com.emad.dummyproducts.data.model.AllProductsResponse
import com.emad.dummyproducts.data.model.ProductDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v{version}/all-product")
    suspend fun getAllProducts(
        @Path("version") version: Int = 1,
        @Query("page") page: Int
    ): AllProductsResponse

    @GET("v{version}/product/{id}")
    suspend fun getProductDetails(
        @Path("version") version: Int = 1,
        @Path("id") id: Int
    ): ProductDetails
}