package com.alijan.tripay.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.R
import com.alijan.tripay.data.model.Service
import com.alijan.tripay.databinding.FragmentHomeBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.ServiceAdapter
import com.alijan.tripay.ui.adapter.TransactionAdapter
import com.alijan.tripay.utils.showFancyToast
import com.alijan.tripay.utils.toTransactionModelList
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by viewModels<HomeViewModel>()
    private val serviceAdapter = ServiceAdapter()
    private val transactionAdapter = TransactionAdapter()
    private val serviceList = arrayListOf(
        Service(R.drawable.icon_sim_service, "Mobil"),
        Service(R.drawable.icon_electric_service, "Elektrik"),
        Service(R.drawable.icon_gas_service, "Qaz"),
        Service(R.drawable.icon_water_service, "Su")
    )

    override fun layoutInflater(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun setupUI() {
        observe()
        setAdapter()
        buttonClick()
    }


    private fun observe() {
        viewModel.user.observe(viewLifecycleOwner) {
            binding.name = it?.userName
            binding.balance = it?.userBalance?.let { it1 -> formatBalance(it1) }
        }
        viewModel.transaction.observe(viewLifecycleOwner) {
            if (it != null) {
                transactionAdapter.updateList(it.toTransactionModelList().reversed())
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            showToastMessage(it, FancyToast.ERROR)
        }
    }

    private fun setAdapter() {
        binding.rvHomeServices.adapter = serviceAdapter
        binding.rvHomeRecentTransaction.adapter = transactionAdapter
        serviceAdapter.updateList(serviceList)
        serviceAdapter.onClick = {
            when (it) {
                "Mobil" -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRechargeFragment())
                }
                "Elektrik" -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToElectricFragment())
                }
                "Qaz" -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToGasFragment())
                }
                "Su" -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToWaterFragment())
                }
            }
        }
    }

    private fun buttonClick() {
        with(binding) {
            buttonHomeSendCash.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSendAmountNav())
            }
            buttonHomeAddFund.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddAmountNav())
            }
        }
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }

    private fun formatBalance(amount: Double): Double {
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(amount).toDouble()
    }


    override fun onResume() {
        super.onResume()
        viewModel.getUserByUserId()
        viewModel.getTransactionsByUserId()
    }

}