package com.cashtrack.api.domain.dto.response

data class BalanceResponse(
    val totalIncomes: Double,
    val totalExpenses: Double,
    val balance: Double,
)
