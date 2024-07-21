package com.alijan.tripay.ui.services.gas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alijan.tripay.R
import com.alijan.tripay.data.model.Brand
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GasViewModel @Inject constructor() : ViewModel() {
    private val listRechargeBrands = arrayListOf(
        Brand("Azəriqaz", R.drawable.img_azeriqaz)
    )

    private var _brands = MutableLiveData<List<Brand>>()
    val brands: LiveData<List<Brand>> get() = _brands

    init {
        updateBrandList()
    }

    private fun updateBrandList(){
        _brands.value = listRechargeBrands
    }
}