package com.emad.dummyproducts.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emad.dummyproducts.data.model.AllProductsResponse
import com.emad.dummyproducts.databinding.ProductItemBinding
import com.emad.dummyproducts.domain.listeners.ProductSelected

class ProductsAdapter: PagingDataAdapter<AllProductsResponse.Product, ProductsAdapter.MyViewHolder>(DIFFUTIL) {
    lateinit var productSelected: ProductSelected
    inner class MyViewHolder(private val mBinding: ProductItemBinding, private val _productSelected: ProductSelected) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(product: AllProductsResponse.Product) {
            mBinding.product = product
            mBinding.productsCardView.setOnClickListener{
                _productSelected.OnProductSelected(product.id)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null)
            holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mBinding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(mBinding, productSelected)
    }

    companion object {
        private val DIFFUTIL = object : DiffUtil.ItemCallback<AllProductsResponse.Product>() {
            override fun areItemsTheSame(
                oldItem: AllProductsResponse.Product,
                newItem: AllProductsResponse.Product,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AllProductsResponse.Product,
                newItem: AllProductsResponse.Product,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun submitProductSelectedListener(iProductSelected: ProductSelected){
        productSelected= iProductSelected
    }
}