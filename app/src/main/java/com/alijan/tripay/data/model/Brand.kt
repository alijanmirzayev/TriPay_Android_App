package com.alijan.tripay.data.model

import androidx.annotation.DrawableRes

data class Brand(val brandName: String, @DrawableRes val image: Int, var isSelected: Boolean = false)
