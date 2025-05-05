package com.cashtrack.api.domain.dto.response

import com.cashtrack.api.domain.enum.IncomeType
import java.io.Serializable
import java.time.LocalDateTime

@Suppress("SerialVersionUIDInSerializableClass")
data class IncomeResponse(
    val id: Long?,
    val incomeLabel: String,
    val value: Double,
    val type: IncomeType,
    val dateCreated: LocalDateTime,
    val lastUpdatedAt: LocalDateTime?,
) : Serializable
