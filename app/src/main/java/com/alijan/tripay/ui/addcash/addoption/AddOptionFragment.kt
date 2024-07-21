package com.alijan.tripay.ui.addcash.addoption

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.tripay.databinding.FragmentAddOptionBinding
import com.alijan.tripay.ui.BaseFragment

class AddOptionFragment : BaseFragment<FragmentAddOptionBinding>() {
    private val args: AddOptionFragmentArgs by navArgs()
    override fun layoutInflater(): FragmentAddOptionBinding =
        FragmentAddOptionBinding.inflate(layoutInflater)

    override fun setupUI() {
        buttonClickListener()
    }

    private fun buttonClickListener() {
        with(binding) {
            imageViewOptionBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonAddOptionCard.setOnClickListener {
                findNavController().navigate(
                    AddOptionFragmentDirections.actionAddOptionFragmentToAddCardFragment(
                        args.amount
                    )
                )
            }
        }
    }

}