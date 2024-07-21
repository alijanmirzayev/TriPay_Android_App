package com.alijan.tripay.ui.services.gas

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.databinding.FragmentGasBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.BrandAdapter
import com.alijan.tripay.ui.services.electric.ElectricFragmentDirections
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast

class GasFragment : BaseFragment<FragmentGasBinding>() {
    private val viewModel by viewModels<GasViewModel>()
    private val brandAdapter = BrandAdapter()
    private var selectedBrand = ""

    override fun layoutInflater(): FragmentGasBinding =
        FragmentGasBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        observe()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.rvGas.adapter = brandAdapter
        brandAdapter.onClick = {
            selectedBrand = it
        }
    }

    private fun observe() {
        viewModel.brands.observe(viewLifecycleOwner) {
            brandAdapter.updateList(it)
        }
    }

    private fun buttonClickListener() {
        with(binding) {
            imageViewGasBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonGas.setOnClickListener {
                val amount = editTextGasAmount.text.toString().trim()
                val number = editTextGasNumber.text.toString().trim()
                if (amount.isNotEmpty() && number.length > 5 && selectedBrand != "") {
                    findNavController().navigate(
                        GasFragmentDirections.actionGasFragmentToConfirmationFragment(
                            amount = amount.toFloat(),
                            serviceCode = number,
                            serviceBrand = selectedBrand
                        )
                    )
                } else {
                    showToastMessage("Xanaları tam və doğru doldurun", FancyToast.WARNING)
                }
            }
        }
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }

}