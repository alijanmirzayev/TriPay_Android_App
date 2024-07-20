package com.alijan.tripay.ui.auth.enterPin

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.R
import com.alijan.tripay.databinding.FragmentConfirmPinBinding
import com.alijan.tripay.databinding.FragmentEnterPinBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.PinNumberAdapter
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class EnterPinFragment : BaseFragment<FragmentEnterPinBinding>() {
    private val viewModel by viewModels<EnterPinViewModel>()
    private val pinNumberAdapter = PinNumberAdapter()
    private val numberList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "✖")
    private var pinCode = ""

    override fun layoutInflater(): FragmentEnterPinBinding =
        FragmentEnterPinBinding.inflate(layoutInflater)

    override fun setupUI() {
        observe()
        setAdapter()
        buttonClickListener()
    }

    private fun observe(){
        viewModel.error.observe(viewLifecycleOwner){
            showToastMessage(it.toString(), FancyToast.ERROR)
            findNavController().navigate(R.id.auth_nav)
        }
    }

    private fun setAdapter() {
        binding.rvEnterPin.adapter = pinNumberAdapter
        pinNumberAdapter.updateList(numberList)
    }

    private fun buttonClickListener() {
        with(binding) {
            pinNumberAdapter.onClickNumber = {
                if (it != "" && it != "✖") {
                    pinCode += it
                    when (pinCode.length) {
                        1 -> imageViewEnterPinEmpty1.setImageResource(R.drawable.full_dot)
                        2 -> imageViewEnterPinEmpty2.setImageResource(R.drawable.full_dot)
                        3 -> imageViewEnterPinEmpty3.setImageResource(R.drawable.full_dot)
                        4 -> imageViewEnterPinEmpty4.setImageResource(R.drawable.full_dot)
                    }
                } else if (it == "✖") {
                    if (pinCode.isNotEmpty()) {
                        pinCode = pinCode.dropLast(1)
                        when (pinCode.length) {
                            0 -> imageViewEnterPinEmpty1.setImageResource(R.drawable.empty_dot)
                            1 -> imageViewEnterPinEmpty2.setImageResource(R.drawable.empty_dot)
                            2 -> imageViewEnterPinEmpty3.setImageResource(R.drawable.empty_dot)
                            3 -> imageViewEnterPinEmpty4.setImageResource(R.drawable.empty_dot)
                        }
                    }
                }

                if(pinCode.length == 4 && pinCode == viewModel.pinCode.value?.userPinCode ?: null){
                    lifecycleScope.launch {
                        showToastMessage("Uğurlu giriş",FancyToast.SUCCESS)
                        delay(1700)
                        findNavController().navigate(EnterPinFragmentDirections.actionSecurityToHome())
                    }
                } else if (pinCode.length == 4) {
                    pinCode = ""
                    showToastMessage("PIN kod doğru deyil",FancyToast.ERROR)
                    imageViewEnterPinEmpty1.setImageResource(R.drawable.empty_dot)
                    imageViewEnterPinEmpty2.setImageResource(R.drawable.empty_dot)
                    imageViewEnterPinEmpty3.setImageResource(R.drawable.empty_dot)
                    imageViewEnterPinEmpty4.setImageResource(R.drawable.empty_dot)
                }
            }
        }
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }
}