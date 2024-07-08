package com.alijan.tripay.ui.auth.confirmPin

import com.alijan.tripay.R
import com.alijan.tripay.databinding.FragmentConfirmPinBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.PinNumberAdapter

class ConfirmPinFragment : BaseFragment<FragmentConfirmPinBinding>() {
    private val pinNumberAdapter = PinNumberAdapter()
    private val numberList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "✖")
    private var pinCode = ""

    override fun layoutInflater(): FragmentConfirmPinBinding =
        FragmentConfirmPinBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.rvConfirmPin.adapter = pinNumberAdapter
        pinNumberAdapter.updateList(numberList)
    }

    private fun buttonClickListener() {
        with(binding) {
            pinNumberAdapter.onClickNumber = {
                if (it != "" && it != "✖") {
                    pinCode += it
                    when (pinCode.length) {
                        1 -> imageViewConfirmPinEmpty1.setImageResource(R.drawable.full_dot)
                        2 -> imageViewConfirmPinEmpty2.setImageResource(R.drawable.full_dot)
                        3 -> imageViewConfirmPinEmpty3.setImageResource(R.drawable.full_dot)
                        4 -> imageViewConfirmPinEmpty4.setImageResource(R.drawable.full_dot)
                    }
                } else if (it == "✖") {
                    if (pinCode.isNotEmpty()) {
                        pinCode = pinCode.dropLast(1)
                        when (pinCode.length) {
                            0 -> imageViewConfirmPinEmpty1.setImageResource(R.drawable.empty_dot)
                            1 -> imageViewConfirmPinEmpty2.setImageResource(R.drawable.empty_dot)
                            2 -> imageViewConfirmPinEmpty3.setImageResource(R.drawable.empty_dot)
                            3 -> imageViewConfirmPinEmpty4.setImageResource(R.drawable.empty_dot)
                        }
                    }
                }
            }
        }
    }

}