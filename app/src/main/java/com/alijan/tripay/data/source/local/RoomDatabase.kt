package com.alijan.tripay.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alijan.tripay.data.model.DTO.PinCodeLocalDTO
import com.alijan.tripay.data.model.DTO.TransactionLocalDTO
import com.alijan.tripay.data.model.DTO.UserLocalDTO

@Database(
    entities = [UserLocalDTO::class, PinCodeLocalDTO::class, TransactionLocalDTO::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}