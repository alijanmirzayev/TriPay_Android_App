package com.alijan.tripay.ui.auth.createPin

import com.alijan.tripay.R
import com.alijan.tripay.databinding.FragmentCreatePinBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.PinNumberAdapter

class CreatePinFragment : BaseFragment<FragmentCreatePinBinding>() {
    private val pinNumberAdapter = PinNumberAdapter()
    private val numberList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "✖")
    private var pinCode = ""

    override fun layoutInflater(): FragmentCreatePinBinding =
        FragmentCreatePinBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.rvCreatePin.adapter = pinNumberAdapter
        pinNumberAdapter.updateList(numberList)
    }

    private fun buttonClickListener() {
        with(binding) {
            pinNumberAdapter.onClickNumber = {
                if (it != "" && it != "✖") {
                    pinCode += it
                    when (pinCode.length) {
                        1 -> imageViewCreatePinEmpty1.setImageResource(R.drawable.full_dot)
                        2 -> imageViewCreatePinEmpty2.setImageResource(R.drawable.full_dot)
                        3 -> imageViewCreatePinEmpty3.setImageResource(R.drawable.full_dot)
                        4 -> imageViewCreatePinEmpty4.setImageResource(R.drawable.full_dot)
                    }
                } else if (it == "✖") {
                    if (pinCode.isNotEmpty()) {
                        pinCode = pinCode.dropLast(1)
                        when (pinCode.length) {
                            0 -> imageViewCreatePinEmpty1.setImageResource(R.drawable.empty_dot)
                            1 -> imageViewCreatePinEmpty2.setImageResource(R.drawable.empty_dot)
                            2 -> imageViewCreatePinEmpty3.setImageResource(R.drawable.empty_dot)
                            3 -> imageViewCreatePinEmpty4.setImageResource(R.drawable.empty_dot)
                        }
                    }
                }
            }
        }
    }

}