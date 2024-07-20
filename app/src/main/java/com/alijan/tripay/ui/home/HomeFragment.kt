package com.alijan.tripay.ui.home

import android.util.Log
import androidx.navigation.fragment.navArgs
import com.alijan.tripay.R
import com.alijan.tripay.data.model.Service
import com.alijan.tripay.databinding.FragmentHomeBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.ServiceAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val args: HomeFragmentArgs by navArgs()
    private val serviceAdapter = ServiceAdapter()
    private val serviceList = arrayListOf(
        Service(R.drawable.icon_sim_service,"Mobil"),
        Service(R.drawable.icon_electric_service,"Elektrik"),
        Service(R.drawable.icon_gas_service,"Qaz"),
        Service(R.drawable.icon_water_service,"Su")
    )


    override fun layoutInflater(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        Log.e("salam2", args.toString())
    }

    private fun setAdapter(){
        binding.rvHomeServices.adapter = serviceAdapter
        serviceAdapter.updateList(serviceList)
    }

}