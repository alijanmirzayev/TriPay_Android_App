package com.alijan.tripay.utils

import com.alijan.tripay.data.model.DTO.TransactionLocalDTO
import com.alijan.tripay.data.model.Transaction

fun TransactionLocalDTO.toTransactionModel(): Transaction{
    return Transaction(
        type = type,
        description = description,
        amount = amount,
    )
}

fun List<TransactionLocalDTO>.toTransactionModelList(): List<Transaction> {
    return this.map { it.toTransactionModel() }
}
