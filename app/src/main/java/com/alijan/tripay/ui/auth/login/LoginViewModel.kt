package com.alijan.tripay.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.tripay.data.model.DTO.UserLocalDTO
import com.alijan.tripay.data.repository.AuthRepository
import com.alijan.tripay.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private var _user = MutableLiveData<UserLocalDTO?>()
    val user: LiveData<UserLocalDTO?> get() = _user

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getUserByPhoneNumberAndMail(phoneNumber: String, mail: String) {
        viewModelScope.launch {
            authRepository.getUserByPhoneNumberAndMail(phoneNumber, mail).collect {
                when (it) {
                    is NetworkResponse.Error -> {
                        _error.value = it.message
                    }

                    is NetworkResponse.Success -> {
                        _user.value = it.data
                    }
                }
            }
        }
    }

}