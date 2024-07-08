package com.alijan.tripay.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setImageFromLocal")
fun setImageFromLocal(imageView: ImageView, @DrawableRes image: Int){
    imageView.setImageResource(image)
}