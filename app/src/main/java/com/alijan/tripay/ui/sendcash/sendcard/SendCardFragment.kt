package com.alijan.tripay.ui.sendcash.sendcard

import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.tripay.databinding.FragmentSendCardBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast

class SendCardFragment : BaseFragment<FragmentSendCardBinding>() {
    private val args: SendCardFragmentArgs by navArgs()

    override fun layoutInflater(): FragmentSendCardBinding = FragmentSendCardBinding.inflate(layoutInflater)

    override fun setupUI() {
        buttonClickListener()
        editTextWatcher()
    }

    private fun buttonClickListener(){
        with(binding){
            imageViewOptionBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonSendCard.setOnClickListener {
                val cardHolder = binding.editTextSendCardHolder.text.toString().trim()
                val cardNumber = binding.editTextSendCardNumber.text.toString().trim()
                if(cardHolder.isNotEmpty() && cardNumber.length == 19){
                    findNavController().navigate(SendCardFragmentDirections.actionSendCardFragmentToTransactionAuthFragment(args.amount, extractLastFourDigits(cardNumber)))
                } else {
                    showToastMessage("Xanaları tam və doğru doldurun", FancyToast.WARNING)
                }
            }
        }
    }

    private fun editTextWatcher(){
        binding.editTextSendCardHolder.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.textViewSendCardHolder.setText(s.toString())
            }
        })

        binding.editTextSendCardNumber.addTextChangedListener(object : TextWatcher {
            private var isFormatting: Boolean = false
            private var deleteLastChar: Boolean = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s != null && s.isNotEmpty()) {
                    deleteLastChar = count > after
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (isFormatting) return

                isFormatting = true

                if (s != null) {
                    if (s.length > 19) {
                        s.delete(19, s.length)
                    }

                    val formattedString = s.toString().replace(" ", "")

                    val newFormattedString = StringBuilder()
                    for (i in formattedString.indices) {
                        if (i % 4 == 0 && i != 0) {
                            newFormattedString.append(" ")
                        }
                        newFormattedString.append(formattedString[i])
                    }

                    binding.editTextSendCardNumber.removeTextChangedListener(this)
                    binding.editTextSendCardNumber.setText(newFormattedString)
                    binding.editTextSendCardNumber.setSelection(newFormattedString.length)
                    binding.editTextSendCardNumber.addTextChangedListener(this)
                    binding.textViewSendCardNumber.setText(newFormattedString)
                }

                isFormatting = false
            }
        })

    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }

    private fun extractLastFourDigits(input: String): String {
        val digits = input.filter { it.isDigit() }
        return if (digits.length >= 4) {
            digits.takeLast(4)
        } else {
            "0000"
        }
    }

}