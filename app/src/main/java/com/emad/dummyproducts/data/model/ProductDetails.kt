package com.emad.dummyproducts.data.model

data class ProductDetails(
    val code: Int,
    val data: ProductInfo,
){
    data class ProductInfo(
        val active: String,
        val describtion: String,
        val id: Int,
        val image: String,
        val name: String,
        val price: String
    )
}