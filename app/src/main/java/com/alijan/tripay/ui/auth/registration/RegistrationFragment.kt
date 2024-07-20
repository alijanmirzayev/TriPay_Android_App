package com.alijan.tripay.ui.auth.registration

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.data.model.DTO.UserLocalDTO
import com.alijan.tripay.databinding.FragmentRegistrationBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.auth.login.LoginFragmentDirections
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
                val name =  editTextRegistrationName.text.toString().trim()
                val mail = editTextRegistrationEmail.text.toString().trim()
                val phoneNumber = "+994${editTextRegistrationPhoneNumber.text.toString().trim()}"
                val value = UserLocalDTO(userName = name, userMail = mail, userPhoneNumber = phoneNumber)
                viewModel.insertUser(value)
            }
        }
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }

}