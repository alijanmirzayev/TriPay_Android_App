package com.alijan.tripay.ui.auth.registration

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.data.model.DTO.UserLocalDTO
import com.alijan.tripay.databinding.FragmentRegistrationBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {
    private val viewModel by viewModels<RegistrationViewModel>()

    override fun layoutInflater(): FragmentRegistrationBinding =
        FragmentRegistrationBinding.inflate(layoutInflater)

    override fun setupUI() {
        observeData()
        buttonClickListener()
    }

    private fun observeData() {
        with(viewModel) {
            isSuccess.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    showToastMessage("Qeydiyyat uğurla tamamlandı!", FancyToast.SUCCESS)
                    delay(1500)
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun buttonClickListener() {
        with(binding) {
            textViewRegistrationAuthText.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonRegistration.setOnClickListener {
                val name = editTextRegistrationName.text.toString().trim()
                val mail = editTextRegistrationEmail.text.toString().trim()
                val phoneNumber = "+994${editTextRegistrationPhoneNumber.text.toString().trim()}"
                if (phoneNumber.length == 13 && mail.isNotEmpty() && name.isNotEmpty()) {
                    if(checkBox.isChecked){
                        val value = UserLocalDTO(
                            userName = name,
                            userMail = mail,
                            userPhoneNumber = phoneNumber,
                            userBalance = 0.00
                        )
                        viewModel.insertUser(value)
                    } else {
                        showToastMessage("Qaydalar qəbul edin", FancyToast.WARNING)
                    }
                } else {
                    showToastMessage("Xanaları tam və düzgün doldurun", FancyToast.WARNING)
                }
            }
        }
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }

}