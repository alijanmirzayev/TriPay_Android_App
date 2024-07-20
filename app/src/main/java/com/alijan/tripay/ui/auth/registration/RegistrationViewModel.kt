package com.alijan.tripay.ui.auth.registration

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
class RegistrationViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private var _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun insertUser(value: UserLocalDTO){
        viewModelScope.launch {
            authRepository.insertUser(value).collect{
                when(it){
                    is NetworkResponse.Error -> {
                        _error.value = it.message
                        _isSuccess.value = false
                    }
                    is NetworkResponse.Success -> {
                        _isSuccess.value = true
                    }
                }
            }
        }
    }

}