package com.emad.dummyproducts.presentation.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.emad.dummyproducts.R

class BindingAdapters {
    companion object {
        @BindingAdapter(value = ["loadImage"], requireAll = true)
        @JvmStatic
        fun loadImage(img: ImageView, imageUrl: String?) {
            Glide.with(img.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(img)
        }
    }
}