package com.cashtrack.cashtrack_api.domain.dto.response

import com.cashtrack.cashtrack_api.domain.enum.ExpenseType
import java.io.Serializable
import java.time.LocalDateTime

@Suppress("SerialVersionUIDInSerializableClass")
data class ExpenseResponse(
    val id: Long?,
    val expenseLabel: String,
    val value: Double,
    val type: ExpenseType,
    val dateCreated: LocalDateTime,
    val lastUpdatedAt: LocalDateTime?
): Serializable
