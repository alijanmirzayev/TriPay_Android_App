package com.alijan.tripay.ui.services.electric

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alijan.tripay.R
import com.alijan.tripay.data.model.Brand
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ElectricViewModel @Inject constructor() : ViewModel() {
    private val listRechargeBrands = arrayListOf(
        Brand("Azərişıq", R.drawable.img_azerisiq)
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