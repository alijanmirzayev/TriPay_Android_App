package com.alijan.tripay.data.model.DTO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pins")
data class PinCodeLocalDTO(
    @PrimaryKey
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "user_pin_code") val userPinCode: String,
)