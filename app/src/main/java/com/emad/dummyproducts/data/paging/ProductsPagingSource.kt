package com.emad.dummyproducts.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.emad.dummyproducts.data.datasources.remote.ApiService
import com.emad.dummyproducts.data.model.AllProductsResponse
import java.lang.Exception

class ProductsPagingSource constructor(private val apiService: ApiService): PagingSource<Int, AllProductsResponse.Product>() {
    private val firstIndex = 1

    override fun getRefreshKey(state: PagingState<Int, AllProductsResponse.Product>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllProductsResponse.Product> {
        val position = params.key ?: firstIndex
        return try {
            val response = apiService.getAllProducts(page = position)
            val list= response.data
            LoadResult.Page(
                data = list,
                prevKey = if (position == firstIndex) null else position - 1,
                nextKey = if (list.isEmpty()) null else position + 1)

        } catch (ex: Exception) {
            val list= mutableListOf<AllProductsResponse.Product>()
            LoadResult.Page(
                data = list,
                prevKey = if (position == firstIndex) null else position - 1,
                nextKey = if (list.isEmpty()) null else position + 1)
        }
    }
}