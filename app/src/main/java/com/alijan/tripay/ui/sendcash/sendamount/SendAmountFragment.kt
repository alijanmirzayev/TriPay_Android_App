package com.alijan.tripay.ui.sendcash.sendamount

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.databinding.FragmentSendAmountBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.PinNumberAdapter
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendAmountFragment : BaseFragment<FragmentSendAmountBinding>() {
    private val viewModel by viewModels<SendAmountViewModel>()
    private val pinNumberAdapter = PinNumberAdapter()
    private val numberList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "✖")
    private var amount = ""

    override fun layoutInflater(): FragmentSendAmountBinding =
        FragmentSendAmountBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.item = "0"
        binding.rvSendAmount.adapter = pinNumberAdapter
        pinNumberAdapter.updateList(numberList)
    }

    private fun buttonClickListener() {
        with(binding) {
            pinNumberAdapter.onClickNumber = {
                if (it != "✖") {
                    amount += it
                    item = amount
                } else if (it == "✖") {
                    if (amount.isNotEmpty()) {
                        amount = amount.dropLast(1)
                        item = amount
                    } else {
                        item = "0"
                    }
                }
            }
            imageViewSendAmountClose.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonSendAmount.setOnClickListener {
                if(amount != ""){
                    if (viewModel.user.value?.userBalance ?: 0.00 > amount.toDouble()) {
                            findNavController().navigate(
                                SendAmountFragmentDirections.actionSendAmountFragmentToSendOptionFragment(
                                    amount = amount.toFloat()
                                )
                            )
                    } else {
                        showToastMessage("Balansınızda kifayət qədər məbləğ yoxdur", FancyToast.WARNING)
                    }
                } else {
                    showToastMessage("Məbləğ daxil edin", FancyToast.WARNING)
                }
            }
        }

    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }

}