package com.emad.dummyproducts.presentation.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emad.dummyproducts.data.model.AllProductsResponse
import com.emad.dummyproducts.databinding.ProductItemBinding

class ProductsAdapter: PagingDataAdapter<AllProductsResponse.Product, ProductsAdapter.MyViewHolder>(DIFFUTIL) {
    inner class MyViewHolder(private val mBinding: ProductItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(product: AllProductsResponse.Product) {
            mBinding.product = product
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null)
            holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mBinding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(mBinding)
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
}