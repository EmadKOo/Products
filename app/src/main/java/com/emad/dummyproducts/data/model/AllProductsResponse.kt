package com.emad.dummyproducts.data.model

data class AllProductsResponse(
    val code: Int,
    val `data`: List<Product>,
    val item: String,
    val message: String,
    val pagination: Pagination
){
    data class Product(
        val id: Int,
        val image: String,
        val name: String
    )
    data class Pagination(
        val nextpage: String?
    )
}