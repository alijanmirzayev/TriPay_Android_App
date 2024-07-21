package com.alijan.tripay.ui.services.response

import androidx.navigation.fragment.findNavController
import com.alijan.tripay.databinding.FragmentResponseServiceBinding
import com.alijan.tripay.ui.BaseFragment

class ResponseServiceFragment : BaseFragment<FragmentResponseServiceBinding>() {
    override fun layoutInflater(): FragmentResponseServiceBinding = FragmentResponseServiceBinding.inflate(layoutInflater)

    override fun setupUI() {
        buttonClickListener()
    }

    private fun buttonClickListener(){
        with(binding){
            imageViewResponseServiceBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonResponseService.setOnClickListener {
                findNavController().navigate(ResponseServiceFragmentDirections.actionResponseServiceFragmentToHomeFragment())
            }
        }
    }

}