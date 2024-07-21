package com.alijan.tripay.ui.services.water

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.databinding.FragmentWaterBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.BrandAdapter
import com.alijan.tripay.ui.services.gas.GasFragmentDirections
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaterFragment : BaseFragment<FragmentWaterBinding>() {
    private val viewModel by viewModels<WaterViewModel>()
    private val brandAdapter = BrandAdapter()
    private var selectedBrand = ""

    override fun layoutInflater(): FragmentWaterBinding =
        FragmentWaterBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        observe()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.rvWater.adapter = brandAdapter
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
            imageViewWaterBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonWater.setOnClickListener {
                val amount = editTextWaterAmount.text.toString().trim()
                val number = editTextWaterNumber.text.toString().trim()
                if (amount.isNotEmpty() && number.length > 5 && selectedBrand != "") {
                    findNavController().navigate(
                        WaterFragmentDirections.actionWaterFragmentToConfirmationFragment(
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