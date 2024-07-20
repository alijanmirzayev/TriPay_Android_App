package com.alijan.tripay.utils

import android.content.Context
import android.view.Gravity
import com.shashank.sony.fancytoastlib.FancyToast

fun Context.showFancyToast(message: String, type: Int) {
    val toast = FancyToast.makeText(
        this,
        message,
        FancyToast.LENGTH_SHORT,
        type,
        false
    )
    toast.setGravity(Gravity.TOP, 0, 150)
    toast.show()
}