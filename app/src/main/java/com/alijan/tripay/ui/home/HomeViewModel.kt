package com.alijan.tripay.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.tripay.data.model.DTO.TransactionLocalDTO
import com.alijan.tripay.data.model.DTO.UserLocalDTO
import com.alijan.tripay.data.repository.AuthRepository
import com.alijan.tripay.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private var _user = MutableLiveData<UserLocalDTO?>()
    val user: LiveData<UserLocalDTO?> get() = _user

    private var _transactions = MutableLiveData<List<TransactionLocalDTO>>()
    val transaction: LiveData<List<TransactionLocalDTO>?> get() = _transactions

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getUserByUserId()
        getTransactionsByUserId()
    }

    fun getUserByUserId() {
        viewModelScope.launch {
            val userId = authRepository.getUserIdFromDatastore()
            authRepository.getUserByUserId(userId!!).collect {
                when (it) {
                    is NetworkResponse.Error -> {
                        it.message?.let { data ->
                            _error.value = data
                        }
                    }

                    is NetworkResponse.Success -> {
                        it.data?.let { data ->
                            _user.value = data
                        }
                    }
                }
            }
        }
    }

    fun getTransactionsByUserId() {
        viewModelScope.launch {
            val userId = authRepository.getUserIdFromDatastore()
            authRepository.getTransactionsByUserId(userId!!).collect {
                when (it) {
                    is NetworkResponse.Error -> {
                        it.message?.let { data ->
                            _error.value = data
                        }
                    }

                    is NetworkResponse.Success -> {
                        it.data?.let { data ->
                            _transactions.value = data
                        }
                    }
                }
            }
        }
    }

}