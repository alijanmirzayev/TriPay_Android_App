package com.alijan.tripay.ui.addcash.responsemessageadd

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.tripay.databinding.FragmentResponseMessageAddBinding
import com.alijan.tripay.ui.BaseFragment

class ResponseMessageAddFragment : BaseFragment<FragmentResponseMessageAddBinding>() {
    private val args: ResponseMessageAddFragmentArgs by navArgs()
    override fun layoutInflater(): FragmentResponseMessageAddBinding =
        FragmentResponseMessageAddBinding.inflate(layoutInflater)

    override fun setupUI() {
        with(binding) {
            textViewResponseMessageAmount.setText("₼${args.amount}0")
            textViewResponseMessage.setText(args.message)
        }
        buttonClickListener()
    }

    private fun buttonClickListener() {
        binding.buttonResponseMessage.setOnClickListener {
            findNavController().navigate(ResponseMessageAddFragmentDirections.actionAddAmountToHome())
        }
    }

}