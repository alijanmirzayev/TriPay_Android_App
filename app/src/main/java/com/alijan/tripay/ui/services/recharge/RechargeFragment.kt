package com.alijan.tripay.ui.services.recharge

import com.alijan.tripay.R
import com.alijan.tripay.data.model.Brand
import com.alijan.tripay.databinding.FragmentRechargeBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.BrandAdapter

class RechargeFragment : BaseFragment<FragmentRechargeBinding>() {
    private val brandAdapter = BrandAdapter()
    private val listRechargeBrand = arrayListOf(
        Brand("Azercell", R.drawable.icon_azerbaijan),
        Brand("Azercell", R.drawable.icon_azerbaijan, true),
        Brand("Azercell", R.drawable.icon_azerbaijan),
    )
    override fun layoutInflater(): FragmentRechargeBinding = FragmentRechargeBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()

    }

    private fun setAdapter(){
        binding.rvRecharge.adapter = brandAdapter
        brandAdapter.updateList(listRechargeBrand)
    }

}