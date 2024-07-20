package com.alijan.tripay.ui.auth.enterPin

import com.alijan.tripay.R
import com.alijan.tripay.databinding.FragmentConfirmPinBinding
import com.alijan.tripay.databinding.FragmentEnterPinBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.PinNumberAdapter

class EnterPinFragment : BaseFragment<FragmentEnterPinBinding>() {
    private val pinNumberAdapter = PinNumberAdapter()
    private val numberList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "✖")
    private var pinCode = ""

    override fun layoutInflater(): FragmentEnterPinBinding =
        FragmentEnterPinBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.rvEnterPin.adapter = pinNumberAdapter
        pinNumberAdapter.updateList(numberList)
    }

    private fun buttonClickListener() {
        with(binding) {
            pinNumberAdapter.onClickNumber = {
                if (it != "" && it != "✖") {
                    pinCode += it
                    when (pinCode.length) {
                        1 -> imageViewEnterPinEmpty1.setImageResource(R.drawable.full_dot)
                        2 -> imageViewEnterPinEmpty2.setImageResource(R.drawable.full_dot)
                        3 -> imageViewEnterPinEmpty3.setImageResource(R.drawable.full_dot)
                        4 -> imageViewEnterPinEmpty4.setImageResource(R.drawable.full_dot)
                    }
                } else if (it == "✖") {
                    if (pinCode.isNotEmpty()) {
                        pinCode = pinCode.dropLast(1)
                        when (pinCode.length) {
                            0 -> imageViewEnterPinEmpty1.setImageResource(R.drawable.empty_dot)
                            1 -> imageViewEnterPinEmpty2.setImageResource(R.drawable.empty_dot)
                            2 -> imageViewEnterPinEmpty3.setImageResource(R.drawable.empty_dot)
                            3 -> imageViewEnterPinEmpty4.setImageResource(R.drawable.empty_dot)
                        }
                    }
                }
            }
        }
    }
}