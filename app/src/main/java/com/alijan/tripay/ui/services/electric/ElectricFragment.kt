package com.alijan.tripay.ui.services.electric

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.databinding.FragmentElectricBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.BrandAdapter
import com.alijan.tripay.ui.services.recharge.RechargeFragmentDirections
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElectricFragment : BaseFragment<FragmentElectricBinding>() {
    private val viewModel by viewModels<ElectricViewModel>()
    private val brandAdapter = BrandAdapter()
    private var selectedBrand = ""

    override fun layoutInflater(): FragmentElectricBinding =
        FragmentElectricBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        observe()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.rvElectric.adapter = brandAdapter
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
            imageViewElectricBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonElectric.setOnClickListener {
                val amount = editTextElectricAmount.text.toString().trim()
                val number = editTextElectricNumber.text.toString().trim()
                if (amount.isNotEmpty() && number.length > 5 && selectedBrand != "") {
                    findNavController().navigate(
                        ElectricFragmentDirections.actionElectricFragmentToConfirmationFragment(
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