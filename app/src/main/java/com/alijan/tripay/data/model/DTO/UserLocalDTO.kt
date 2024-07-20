package com.alijan.tripay.data.model.DTO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserLocalDTO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id") val userId: Int? = null,
    @ColumnInfo(name = "user_name") val userName: String?,
    @ColumnInfo(name = "user_mail") val userMail: String?,
    @ColumnInfo(name = "user_phone_number") val userPhoneNumber: String?,
    @ColumnInfo(name = "user_balance") val userBalance: Double,
)
