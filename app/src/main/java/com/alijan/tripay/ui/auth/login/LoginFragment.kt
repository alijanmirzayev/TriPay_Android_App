package com.alijan.tripay.ui.auth.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.databinding.FragmentLoginBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.utils.showFancyToast
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel by viewModels<LoginViewModel>()
    override fun layoutInflater(): FragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)

    override fun setupUI() {
        observeData()
        buttonClickListener()
    }

    private fun observeData() {
        with(viewModel) {
            user.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    if(it != null){
                        showToastMessage("Uğurlu giriş!", FancyToast.SUCCESS)

                        delay(1700)
                        it.userId?.let { userId ->
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCreatePinFragment(userId))
                        }

                    } else if (it == null) {
                        showToastMessage("E-mail adresi vəya nömrə yanlışdır!", FancyToast.ERROR)
                    }
                }
            }
        }
    }

    private fun buttonClickListener(){
        with(binding){
            textViewLoginAuthText.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
            }
            buttonLogin.setOnClickListener {
                val phoneNumber = "+994${editTextLoginPhoneNumber.text.toString().trim()}"
                val mail = editTextLoginEmail.text.toString().trim()
                viewModel.getUserByPhoneNumberAndMail(phoneNumber, mail)
            }
        }
    }

    private fun showToastMessage(message: String, type: Int) {
        requireContext().showFancyToast(message, type)
    }

}