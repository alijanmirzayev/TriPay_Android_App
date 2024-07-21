package com.alijan.tripay.ui.responsemessage

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.tripay.databinding.FragmentResponseMessageBinding
import com.alijan.tripay.ui.BaseFragment

class ResponseMessageFragment : BaseFragment<FragmentResponseMessageBinding>() {
    private val args: ResponseMessageFragmentArgs by navArgs()
    override fun layoutInflater(): FragmentResponseMessageBinding =
        FragmentResponseMessageBinding.inflate(layoutInflater)

    override fun setupUI() {
        with(binding) {
            textViewResponseMessageAmount.setText("₼${args.amount}0")
            textViewResponseMessage.setText(args.message)
        }
        buttonClickListener()
    }

    private fun buttonClickListener() {
        binding.buttonResponseMessage.setOnClickListener {
            findNavController().navigate(ResponseMessageFragmentDirections.actionSendAmountToHome())
        }
    }

}