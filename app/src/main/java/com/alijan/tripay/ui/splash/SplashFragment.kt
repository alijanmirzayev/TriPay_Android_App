package com.alijan.tripay.ui.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alijan.tripay.R
import com.alijan.tripay.data.repository.AuthRepository
import com.alijan.tripay.databinding.FragmentSplashBinding
import com.alijan.tripay.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    @Inject
    lateinit var authRepository: AuthRepository
    override fun layoutInflater(): FragmentSplashBinding = FragmentSplashBinding.inflate(layoutInflater)

    override fun setupUI() {
        lifecycleScope.launch {
            val response = authRepository.getUserIdFromDatastore()
            if(response != null){
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToEnterPinFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToAuthNav())
            }
        }
    }

}