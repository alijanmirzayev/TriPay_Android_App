package com.alijan.tripay.ui.addcash.addamount

import androidx.navigation.fragment.findNavController
import com.alijan.tripay.databinding.FragmentAddAmountBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.PinNumberAdapter
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast

class AddAmountFragment : BaseFragment<FragmentAddAmountBinding>() {
    private val pinNumberAdapter = PinNumberAdapter()
    private val numberList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "✖")
    private var amount = ""

    override fun layoutInflater(): FragmentAddAmountBinding =
        FragmentAddAmountBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.item = "0"
        binding.rvAddAmount.adapter = pinNumberAdapter
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
            imageViewAddAmountClose.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonAddAmount.setOnClickListener {
                if (amount != "") {
                    findNavController().navigate(
                        AddAmountFragmentDirections.actionAddAmountFragmentToAddOptionFragment(
                            amount = amount.toFloat()
                        )
                    )
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