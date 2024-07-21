package com.alijan.tripay.ui.services

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
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class ConfirmationViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    private var _isSuccess = MutableLiveData<Boolean>(false)
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private var _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error
    fun insert(amount: Float, serviceCode: String, serviceBrand: String) {
        viewModelScope.launch {
            val userId = authRepository.getUserIdFromDatastore()

            userId?.let {
                authRepository.getUserByUserId(it).collect { response ->
                    when (response) {
                        is NetworkResponse.Error -> {
                            _error.value = response.message
                        }

                        is NetworkResponse.Success -> {
                            authRepository.insertTransaction(
                                TransactionLocalDTO(
                                    userId = response.data?.userId!!,
                                    amount = formatAmount(amount.toDouble()),
                                    type = "Ödəmə edildi: ${serviceCode}",
                                    description = serviceBrand
                                )
                            ).collect {
                                authRepository.insertUser(
                                    UserLocalDTO(
                                        userId = response.data.userId,
                                        userName = response.data.userName,
                                        userMail = response.data.userMail,
                                        userBalance = response.data.userBalance - amount.toDouble(),
                                        userPhoneNumber = response.data.userPhoneNumber
                                    )
                                ).collect{
                                    _isSuccess.value = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun formatAmount(number: Double): Double {
        val decimalFormat = DecimalFormat("#.##")
        return decimalFormat.format(number).toDouble()
    }


}