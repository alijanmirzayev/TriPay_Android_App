package com.alijan.tripay.ui.sendcash.sendoption

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.tripay.databinding.FragmentSendOptionBinding
import com.alijan.tripay.ui.BaseFragment

class SendOptionFragment : BaseFragment<FragmentSendOptionBinding>() {
    private val args: SendOptionFragmentArgs by navArgs()
    override fun layoutInflater(): FragmentSendOptionBinding = FragmentSendOptionBinding.inflate(layoutInflater)

    override fun setupUI() {
        buttonClickListener()
    }

    private fun buttonClickListener(){
        with(binding){
            imageViewOptionBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonSendOptionCard.setOnClickListener {
                findNavController().navigate(SendOptionFragmentDirections.actionSendOptionFragmentToSendCardFragment(args.amount))
            }
        }
    }

}