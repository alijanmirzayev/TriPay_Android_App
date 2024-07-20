package com.alijan.tripay.ui.auth.confirmPin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.tripay.data.model.DTO.PinCodeLocalDTO
import com.alijan.tripay.data.model.DTO.UserLocalDTO
import com.alijan.tripay.data.repository.AuthRepository
import com.alijan.tripay.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private var _pinCode = MutableLiveData<PinCodeLocalDTO?>()
    val pinCode: LiveData<PinCodeLocalDTO?> get() = _pinCode

    private var _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getPinCodeByUserId(userId: Int){
        viewModelScope.launch {
            authRepository.getPinCodeByUserId(userId).collect{
                when(it){
                    is NetworkResponse.Error -> {
                        it.message?.let {
                            _error.value = it
                        }
                    }
                    is NetworkResponse.Success -> {
                        _pinCode.value = it.data
                    }
                }
            }
        }
    }

    fun insertPinCode(value: PinCodeLocalDTO){
        viewModelScope.launch {
            authRepository.insertPinCode(value).collect{
                when(it){
                    is NetworkResponse.Error -> {
                        it.message?.let {
                            _error.value = it
                        }
                    }
                    is NetworkResponse.Success -> {
                        _isSuccess.value = true
                    }
                }
            }
        }
    }

    fun saveUserIdToDataStore(userId: Int) {
        viewModelScope.launch {
            authRepository.saveUserIdToDataStore(userId)
        }
    }

}