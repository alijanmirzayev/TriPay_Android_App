package com.alijan.tripay.data.model.DTO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionLocalDTO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "transaction_id") val transactionId: Int? = null,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "transaction_amount") val amount: Double,
    @ColumnInfo(name = "transaction_type") val type: String,
)
