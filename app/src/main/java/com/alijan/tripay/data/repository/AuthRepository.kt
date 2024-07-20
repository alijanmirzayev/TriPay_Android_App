package com.alijan.tripay.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.alijan.tripay.data.model.DTO.PinCodeLocalDTO
import com.alijan.tripay.data.model.DTO.TransactionLocalDTO
import com.alijan.tripay.data.model.DTO.UserLocalDTO
import com.alijan.tripay.data.source.local.RoomDatabase
import com.alijan.tripay.utils.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val roomDatabase: RoomDatabase,
    private val dataStore: DataStore<Preferences>
) {

    private object PreferencesKeys {
        val USERID_KEY = intPreferencesKey("userId")
    }

    suspend fun saveUserIdToDataStore(value: Int) {
        dataStore.edit {
            it[PreferencesKeys.USERID_KEY] = value
        }
    }

    suspend fun getUserIdFromDatastore(): Int? {
        val preferences = dataStore.data.first()
        val userId = preferences[PreferencesKeys.USERID_KEY] ?: null
        return userId
    }

    suspend fun insertUser(value: UserLocalDTO): Flow<NetworkResponse<Long>> {
        return flow {
            try {
                val response = roomDatabase.userDao().insertUser(value)
                emit(NetworkResponse.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUserByPhoneNumberAndMail(
        phoneNumber: String,
        mail: String
    ): Flow<NetworkResponse<UserLocalDTO?>> {
        return flow {
            try {
                val response = roomDatabase.userDao().getUserByPhoneNumberAndMail(phoneNumber, mail)
                emit(NetworkResponse.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUserByUserId(
        id: Int
    ): Flow<NetworkResponse<UserLocalDTO?>> {
        return flow {
            try {
                val response = roomDatabase.userDao().getUserByUserId(id)
                emit(NetworkResponse.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun insertPinCode(value: PinCodeLocalDTO): Flow<NetworkResponse<Long>> {
        return flow {
            try {
                val response = roomDatabase.userDao().insertPinCode(value)
                emit(NetworkResponse.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPinCodeByUserId(userId: Int): Flow<NetworkResponse<PinCodeLocalDTO?>> {
        return flow {
            try {
                val response = roomDatabase.userDao().getPinCodeByUserId(userId)
                emit(NetworkResponse.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun insertTransaction(value: TransactionLocalDTO): Flow<NetworkResponse<Long>> {
        return flow {
            try {
                val response = roomDatabase.userDao().insertTransaction(value)
                emit(NetworkResponse.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTransactionsByUserId(userId: Int): Flow<NetworkResponse<List<TransactionLocalDTO>>> {
        return flow {
            try {
                val response = roomDatabase.userDao().getTransactionsByUserId(userId)
                emit(NetworkResponse.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}