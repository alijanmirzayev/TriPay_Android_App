package com.alijan.tripay.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alijan.tripay.data.model.DTO.PinCodeLocalDTO
import com.alijan.tripay.data.model.DTO.UserLocalDTO

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(value: UserLocalDTO): Long

    @Query("SELECT * FROM users WHERE user_phone_number = :phoneNumber AND user_mail = :mail")
    suspend fun getUserByPhoneNumberAndMail(phoneNumber: String, mail: String): UserLocalDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPinCode(value: PinCodeLocalDTO): Long

    @Query("SELECT * FROM pins WHERE user_id = :userId LIMIT 1")
    suspend fun getPinCodeByUserId(userId: Int): PinCodeLocalDTO?
}
