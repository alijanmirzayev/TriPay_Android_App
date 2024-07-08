package com.alijan.tripay.ui.sendcash.transactionauth

import com.alijan.tripay.R
import com.alijan.tripay.databinding.FragmentTransactionAuthBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.PinNumberAdapter

class TransactionAuthFragment : BaseFragment<FragmentTransactionAuthBinding>() {
    private val pinNumberAdapter = PinNumberAdapter()
    private val numberList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "✖")
    private var pinCode = ""

    override fun layoutInflater(): FragmentTransactionAuthBinding =
        FragmentTransactionAuthBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.rvTransactionAuth.adapter = pinNumberAdapter
        pinNumberAdapter.updateList(numberList)
    }

    private fun buttonClickListener() {
        with(binding) {
            pinNumberAdapter.onClickNumber = {
                if (it != "" && it != "✖") {
                    pinCode += it
                    when (pinCode.length) {
                        1 -> imageViewTransactionAuthEmpty1.setImageResource(R.drawable.full_dot)
                        2 -> imageViewTransactionAuthEmpty2.setImageResource(R.drawable.full_dot)
                        3 -> imageViewTransactionAuthEmpty3.setImageResource(R.drawable.full_dot)
                        4 -> imageViewTransactionAuthEmpty4.setImageResource(R.drawable.full_dot)
                    }
                } else if (it == "✖") {
                    if (pinCode.isNotEmpty()) {
                        pinCode = pinCode.dropLast(1)
                        when (pinCode.length) {
                            0 -> imageViewTransactionAuthEmpty1.setImageResource(R.drawable.empty_dot)
                            1 -> imageViewTransactionAuthEmpty2.setImageResource(R.drawable.empty_dot)
                            2 -> imageViewTransactionAuthEmpty3.setImageResource(R.drawable.empty_dot)
                            3 -> imageViewTransactionAuthEmpty4.setImageResource(R.drawable.empty_dot)
                        }
                    }
                }
            }
        }
    }

}