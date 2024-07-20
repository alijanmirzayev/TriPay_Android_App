package com.alijan.tripay.ui.sendcash.sendamount

import androidx.navigation.fragment.findNavController
import com.alijan.tripay.databinding.FragmentSendAmountBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.PinNumberAdapter

class SendAmountFragment : BaseFragment<FragmentSendAmountBinding>() {
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
                findNavController().navigate(SendAmountFragmentDirections.actionSendAmountFragmentToSendOptionFragment(amount = amount.toFloat()))
            }
        }

    }

}