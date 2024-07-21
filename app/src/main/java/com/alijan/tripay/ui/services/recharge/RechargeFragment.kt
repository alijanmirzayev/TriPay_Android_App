package com.alijan.tripay.ui.services.recharge

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.databinding.FragmentRechargeBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.BrandAdapter
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RechargeFragment : BaseFragment<FragmentRechargeBinding>() {
    private val viewModel by viewModels<RechargeViewModel>()
    private val brandAdapter = BrandAdapter()
    private var selectedBrand = ""

    override fun layoutInflater(): FragmentRechargeBinding =
        FragmentRechargeBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        observe()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.rvRecharge.adapter = brandAdapter
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
            imageViewRechargeBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonRecharge.setOnClickListener {
                val amount = editTextRechargeAmount.text.toString().trim()
                val phoneNumber = editTextRechargePhoneNumber.text.toString().trim()
                if (amount.isNotEmpty() && phoneNumber.length == 9 && selectedBrand != "") {
                    findNavController().navigate(
                        RechargeFragmentDirections.actionRechargeFragmentToConfirmationFragment(
                            amount = amount.toFloat(),
                            serviceCode = phoneNumber,
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