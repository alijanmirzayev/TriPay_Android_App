package com.alijan.tripay.ui.auth.confirmPin

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.tripay.R
import com.alijan.tripay.data.model.DTO.PinCodeLocalDTO
import com.alijan.tripay.databinding.FragmentConfirmPinBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.PinNumberAdapter
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConfirmPinFragment : BaseFragment<FragmentConfirmPinBinding>() {
    private val viewModel by viewModels<ConfirmViewModel>()
    private val args: ConfirmPinFragmentArgs by navArgs()
    private val pinNumberAdapter = PinNumberAdapter()
    private val numberList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "✖")
    private var pinCode = ""
    override fun layoutInflater(): FragmentConfirmPinBinding =
        FragmentConfirmPinBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
        buttonClickListener()
    }

    private fun setAdapter() {
        binding.rvConfirmPin.adapter = pinNumberAdapter
        pinNumberAdapter.updateList(numberList)
    }

    private fun buttonClickListener() {
        with(binding) {
            pinNumberAdapter.onClickNumber = {
                if (it != "" && it != "✖") {
                    pinCode += it
                    when (pinCode.length) {
                        1 -> imageViewConfirmPinEmpty1.setImageResource(R.drawable.full_dot)
                        2 -> imageViewConfirmPinEmpty2.setImageResource(R.drawable.full_dot)
                        3 -> imageViewConfirmPinEmpty3.setImageResource(R.drawable.full_dot)
                        4 -> imageViewConfirmPinEmpty4.setImageResource(R.drawable.full_dot)
                    }
                } else if (it == "✖") {
                    if (pinCode.isNotEmpty()) {
                        pinCode = pinCode.dropLast(1)
                        when (pinCode.length) {
                            0 -> imageViewConfirmPinEmpty1.setImageResource(R.drawable.empty_dot)
                            1 -> imageViewConfirmPinEmpty2.setImageResource(R.drawable.empty_dot)
                            2 -> imageViewConfirmPinEmpty3.setImageResource(R.drawable.empty_dot)
                            3 -> imageViewConfirmPinEmpty4.setImageResource(R.drawable.empty_dot)
                        }
                    }
                }

                if (pinCode.length == 4 && pinCode == args.pinCode) {
                    viewModel.insertPinCode(PinCodeLocalDTO(
                        userId = args.userId,
                        userPinCode = args.pinCode
                    ))
                    viewModel.saveUserIdToDataStore(args.userId)
                    lifecycleScope.launch {
                        showToastMessage("Əsas səhifəyə yönləndirilirsiz.", FancyToast.SUCCESS)
                        delay(1700)
                        findNavController().navigate(ConfirmPinFragmentDirections.actionConfirmPinToHome())
                    }
                } else if (pinCode.length == 4) {
                    pinCode = ""
                    showToastMessage("PIN kod yanlışdır", FancyToast.WARNING)
                    imageViewConfirmPinEmpty1.setImageResource(R.drawable.empty_dot)
                    imageViewConfirmPinEmpty2.setImageResource(R.drawable.empty_dot)
                    imageViewConfirmPinEmpty3.setImageResource(R.drawable.empty_dot)
                    imageViewConfirmPinEmpty4.setImageResource(R.drawable.empty_dot)
                }
            }
        }
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }
}