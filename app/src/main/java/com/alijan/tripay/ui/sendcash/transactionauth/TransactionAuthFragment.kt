package com.alijan.tripay.ui.sendcash.transactionauth

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alijan.tripay.R
import com.alijan.tripay.databinding.FragmentTransactionAuthBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.PinNumberAdapter
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransactionAuthFragment : BaseFragment<FragmentTransactionAuthBinding>() {
    private val args: TransactionAuthFragmentArgs by navArgs()
    private val viewModel by viewModels<TransactionAuthViewModel>()
    private val pinNumberAdapter = PinNumberAdapter()
    private val numberList = arrayListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "✖")
    private var pinCode = ""

    override fun layoutInflater(): FragmentTransactionAuthBinding =
        FragmentTransactionAuthBinding.inflate(layoutInflater)

    override fun setupUI() {
        observe()
        setAdapter()
        buttonClickListener()
    }

    private fun observe() {
        viewModel.error.observe(viewLifecycleOwner) {
            showToastMessage("Pin kod tapılmadı", FancyToast.ERROR)
            findNavController().navigate(R.id.auth_nav)
        }
    }

    private fun setAdapter() {
        binding.rvTransactionAuth.adapter = pinNumberAdapter
        pinNumberAdapter.updateList(numberList)
    }

    private fun buttonClickListener() {
        with(binding) {
            pinNumberAdapter.onClickNumber = {
                if (it != "" && it != "✖") {
                    pinCode += it
                    when (pinCode.length) {
                        1 -> imageViewTransactionAuthEmpty1.setImageResource(R.drawable.full_dot)
                        2 -> imageViewTransactionAuthEmpty2.setImageResource(R.drawable.full_dot)
                        3 -> imageViewTransactionAuthEmpty3.setImageResource(R.drawable.full_dot)
                        4 -> imageViewTransactionAuthEmpty4.setImageResource(R.drawable.full_dot)
                    }
                } else if (it == "✖") {
                    if (pinCode.isNotEmpty()) {
                        pinCode = pinCode.dropLast(1)
                        when (pinCode.length) {
                            0 -> imageViewTransactionAuthEmpty1.setImageResource(R.drawable.empty_dot)
                            1 -> imageViewTransactionAuthEmpty2.setImageResource(R.drawable.empty_dot)
                            2 -> imageViewTransactionAuthEmpty3.setImageResource(R.drawable.empty_dot)
                            3 -> imageViewTransactionAuthEmpty4.setImageResource(R.drawable.empty_dot)
                        }
                    }
                }

                if (pinCode.length == 4 && pinCode == viewModel.pinCode.value?.userPinCode ?: null) {
                    viewModel.insert(args.amount)
                    lifecycleScope.launch {
                        showToastMessage("Uğurlu əməliyyat", FancyToast.SUCCESS)
                        delay(1700)
                        findNavController().navigate(
                            TransactionAuthFragmentDirections.actionTransactionAuthFragmentToResponseMessageFragment(
                                args.amount,
                                "Ödəniş uğurlu bir şəkildə göndərildi"
                            )
                        )

                    }
                } else if (pinCode.length == 4) {
                    pinCode = ""
                    showToastMessage("PIN kod doğru deyil", FancyToast.ERROR)
                    imageViewTransactionAuthEmpty1.setImageResource(R.drawable.empty_dot)
                    imageViewTransactionAuthEmpty2.setImageResource(R.drawable.empty_dot)
                    imageViewTransactionAuthEmpty3.setImageResource(R.drawable.empty_dot)
                    imageViewTransactionAuthEmpty4.setImageResource(R.drawable.empty_dot)
                }
            }
            imageViewTransactionAuthClose.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }

}