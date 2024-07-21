package com.alijan.tripay.ui.services

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.tripay.databinding.FragmentConfirmationBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmationFragment : BaseFragment<FragmentConfirmationBinding>() {
    private val args: ConfirmationFragmentArgs by navArgs()
    private val viewModel by viewModels<ConfirmationViewModel>()
    override fun layoutInflater(): FragmentConfirmationBinding = FragmentConfirmationBinding.inflate(layoutInflater)

    override fun setupUI() {
        observe()
        buttonClickListener()
    }

    private fun observe(){
        viewModel.error.observe(viewLifecycleOwner){
            showToastMessage("Xəta baş verdi: ${it}",FancyToast.ERROR)
        }
        viewModel.isSuccess.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(ConfirmationFragmentDirections.actionConfirmationFragmentToResponseServiceFragment())
            }
        }
    }

    private fun buttonClickListener(){
        with(binding){
            imageViewConfirmationBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonConfirmation.setOnClickListener {
                viewModel.insert(args.amount, args.serviceCode, args.serviceBrand)
            }
        }
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }

}