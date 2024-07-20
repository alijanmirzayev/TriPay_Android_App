package com.alijan.tripay.ui.auth.enterPin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.tripay.data.model.DTO.PinCodeLocalDTO
import com.alijan.tripay.data.repository.AuthRepository
import com.alijan.tripay.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterPinViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private var _pinCode = MutableLiveData<PinCodeLocalDTO?>()
    val pinCode: LiveData<PinCodeLocalDTO?> get() = _pinCode

    private var _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        getPinCodeByUserId()
    }
    private fun getPinCodeByUserId(){
        viewModelScope.launch {
            val userId = authRepository.getUserIdFromDatastore()
            if(userId != null){
                authRepository.getPinCodeByUserId(userId).collect{
                    when(it){
                        is NetworkResponse.Error -> {
                            _error.value = it.message
                        }
                        is NetworkResponse.Success -> {
                            it.data?.let { data ->
                                _pinCode.value = data
                            }
                        }
                    }
                }
            } else {
                _error.value = "notFoundPinCode"
            }

        }
    }



}