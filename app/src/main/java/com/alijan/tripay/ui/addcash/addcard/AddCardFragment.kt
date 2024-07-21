package com.alijan.tripay.ui.addcash.addcard

import android.text.Editable
import android.text.TextWatcher
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.tripay.databinding.FragmentAddCardBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.sendcash.sendcard.SendCardFragmentArgs
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast

class AddCardFragment : BaseFragment<FragmentAddCardBinding>() {
    private val args: SendCardFragmentArgs by navArgs()

    override fun layoutInflater(): FragmentAddCardBinding =
        FragmentAddCardBinding.inflate(layoutInflater)

    override fun setupUI() {
        buttonClickListener()
        editTextWatcher()
    }

    private fun buttonClickListener() {
        with(binding) {
            imageViewOptionBack.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonAddCard.setOnClickListener {
                val cardHolder = binding.editTextAddCardHolder.text.toString().trim()
                val cardNumber = binding.editTextAddCardNumber.text.toString().trim()
                val cardValidDate = binding.editTextAddCardValidDate.text.toString().trim()
                val cardCvv = binding.editTextAddCardCvv.text.toString().trim()
                if (cardHolder.isNotEmpty() && cardNumber.length == 19 && cardCvv.length == 3 && cardValidDate.length == 5) {
                    findNavController().navigate(
                        AddCardFragmentDirections.actionAddCardFragmentToTransactionAddAuthFragment(
                            args.amount,
                            extractLastFourDigits(cardNumber)
                        )
                    )
                } else {
                    showToastMessage("Xanaları tam və doğru doldurun", FancyToast.WARNING)
                }
            }
        }
    }

    private fun editTextWatcher() {
        binding.editTextAddCardHolder.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.textViewAddCardHolder.setText(s.toString())
            }
        })

        binding.editTextAddCardNumber.addTextChangedListener(object : TextWatcher {
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

                    binding.editTextAddCardNumber.removeTextChangedListener(this)
                    binding.editTextAddCardNumber.setText(newFormattedString)
                    binding.editTextAddCardNumber.setSelection(newFormattedString.length)
                    binding.editTextAddCardNumber.addTextChangedListener(this)
                    binding.textViewAddCardNumber.setText(newFormattedString)
                }

                isFormatting = false
            }
        })

        binding.editTextAddCardValidDate.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val mmYy = "MMYY"

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    var cleanString = s.toString().replace("[^\\d.]".toRegex(), "")

                    if (cleanString.length > 4) {
                        cleanString = cleanString.substring(0, 4)
                    }

                    val formatted = buildString {
                        if (cleanString.length >= 2) {
                            append(cleanString.substring(0, 2))
                            append("/")
                            append(cleanString.substring(2))
                        } else {
                            append(cleanString)
                        }
                    }

                    current = formatted
                    binding.editTextAddCardValidDate.removeTextChangedListener(this)
                    binding.editTextAddCardValidDate.setText(formatted)
                    binding.editTextAddCardValidDate.setSelection(formatted.length)
                    binding.editTextAddCardValidDate.addTextChangedListener(this)
                    binding.textViewAddCardValidDate.setText(formatted)
                }
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